package api_com_bank.account_movements.services;

import api_com_bank.account_movements.dtos.reports.AccountMovementsReportResponseDTO;
import api_com_bank.account_movements.dtos.reports.AccountsDataReportDTO;
import api_com_bank.account_movements.dtos.reports.ClientReportDTO;
import api_com_bank.account_movements.dtos.reports.MovementReportDTO;
import api_com_bank.account_movements.dtos.response.CustomerResponseDTO;
import api_com_bank.account_movements.entities.AccountEntity;
import api_com_bank.account_movements.entities.MovementsEntity;
import api_com_bank.account_movements.exceptions.ClientErrorException;
import api_com_bank.account_movements.mappers.AccountMapper;
import api_com_bank.account_movements.mappers.MovementsMapper;
import api_com_bank.account_movements.repositories.AccountRepository;
import api_com_bank.account_movements.repositories.MovementsRepository;
import api_com_bank.account_movements.services.implementations.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovementsReportBuilder {

    private final MovementsRepository movementsRepository;
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public AccountMovementsReportResponseDTO buildReport(String clientId, Date startDate, Date endDate) {
        String clientName = fetchClientName(clientId);
        Map<String, List<MovementsEntity>> movementsByAccount = groupMovementsByAccount(clientId, startDate, endDate);
        return buildResponse(clientName, movementsByAccount);
    }

    private String fetchClientName(String clientId) {
        return customerService.getCustomerByIdentificationNumber(clientId)
                .map(CustomerResponseDTO::getName)
                .blockOptional()
                .orElseThrow(() -> new ClientErrorException("Client not found for ID: " + clientId));
    }

    private Map<String, List<MovementsEntity>> groupMovementsByAccount(String clientId, Date startDate, Date endDate) {
        List<MovementsEntity> movements = movementsRepository.findMovementsByClientAndDateRange(clientId, startDate, endDate);

        if (movements.isEmpty()) {
            throw new ClientErrorException("No movements found for client ID: " + clientId);
        }

        return movements.stream()
                .collect(Collectors.groupingBy(MovementsEntity::getAccountNumber));
    }

    private AccountMovementsReportResponseDTO buildResponse(String clientName, Map<String, List<MovementsEntity>> movementsByAccount) {
        ClientReportDTO clientReportDTO = new ClientReportDTO(clientName);

        List<AccountsDataReportDTO> accountsData = movementsByAccount.entrySet().stream()
                .map(entry -> buildAccountReport(entry.getKey(), entry.getValue()))
                .toList();

        return new AccountMovementsReportResponseDTO(clientReportDTO, accountsData);
    }

    private AccountsDataReportDTO buildAccountReport(String accountNumber, List<MovementsEntity> movements) {
        AccountEntity accountEntity = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ClientErrorException("Account not found for number: " + accountNumber));

        List<MovementReportDTO> movementReportDTOs = movements.stream()
                .map(MovementsMapper.INSTANCE::toMovementReportDto)
                .toList();

        return AccountMapper.INSTANCE.toReportDto(accountEntity, movementReportDTOs);
    }

}
