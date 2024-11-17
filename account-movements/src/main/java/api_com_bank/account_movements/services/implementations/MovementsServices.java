package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.reports.AccountMovementsReportResponseDTO;
import api_com_bank.account_movements.dtos.request.CreateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovementsRequestDTO;
import api_com_bank.account_movements.dtos.response.*;
import api_com_bank.account_movements.entities.AccountEntity;
import api_com_bank.account_movements.entities.MovementsEntity;
import api_com_bank.account_movements.exceptions.ClientErrorException;
import api_com_bank.account_movements.mappers.AccountMapper;
import api_com_bank.account_movements.mappers.MovementsMapper;
import api_com_bank.account_movements.repositories.MovementsRepository;
import api_com_bank.account_movements.services.MovementsReportBuilder;
import api_com_bank.account_movements.services.contracts.IAccountServices;
import api_com_bank.account_movements.services.contracts.IMovementsServices;
import api_com_bank.account_movements.utils.functional.BalanceValidator;
import api_com_bank.account_movements.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
public class MovementsServices implements IMovementsServices {

    private final MovementsRepository movementsRepository;
    private final MovementsReportBuilder movementsReportBuilder;
    private final IAccountServices accountServices;

    private static final BalanceValidator balanceValidator = (currentBalance, movementValue) -> currentBalance + movementValue >= 0;

    @Override
    public ResponseDTO create(CreateMovementsRequestDTO createMovementsRequestDTO) {
        log.info("Creating movements: {}", createMovementsRequestDTO);
        MovementsEntity movementsEntity = MovementsMapper.INSTANCE.toEntityCreate(createMovementsRequestDTO);
        movementsRepository.save(movementsEntity);

        return new ResponseDTO(Messages.MOVEMENT_CREATED, "00", new Date());
    }

    @Override
    public ResponseDTO update(UpdateMovementsRequestDTO updateMovementsRequestDTO) {
        MovementsEntity movementToUpdate = movementsRepository.findById(updateMovementsRequestDTO.getId())
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.MOVEMENT_NO_FOUND, updateMovementsRequestDTO.getId())));

        MovementsEntity movementsEntity = MovementsMapper.INSTANCE.toEntityUpdate(updateMovementsRequestDTO);
        movementsEntity.setId(movementToUpdate.getId());
        movementsRepository.save(movementsEntity);

        return new ResponseDTO(Messages.MOVEMENT_UPDATED, "00", new Date());
    }

    @Override
    public ResponseDTO delete(Long id) {
        MovementsEntity movementToDelete = movementsRepository.findById(id)
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.MOVEMENT_NO_FOUND, id)));

        movementsRepository.delete(movementToDelete);
        return new ResponseDTO(Messages.MOVEMENT_DELETED, "00", new Date());
    }

    @Override
    public AccountMovementsReportResponseDTO getMovements(String clientId, Date startDate, Date endDate) {
        log.info("Fetching report for client {} between {} and {}", clientId, startDate, endDate);
        return movementsReportBuilder.buildReport(clientId,startDate, endDate);
    }

    @Override
    public ResponseDTO createValidatedMovement(CreateMovementsRequestDTO createMovementsRequestDTO) {
        log.info("Validating and creating movement: {}", createMovementsRequestDTO);

        AccountEntity accountEntity = AccountMapper.INSTANCE.toEntity(accountServices.getAccount(createMovementsRequestDTO.getAccountNumber()));

        if (!balanceValidator.isValid(accountEntity.getBalance(), createMovementsRequestDTO.getValue())) {
            throw new ClientErrorException("Saldo no disponible");
        }

        Double balanceInitial = accountEntity.getBalance();
        Double updatedBalance = accountEntity.getBalance() + createMovementsRequestDTO.getValue();
        accountEntity.setBalance(updatedBalance);
        accountServices.updateBalance(accountEntity);

        MovementsEntity movementsEntity = MovementsMapper.INSTANCE.toEntityCreate(createMovementsRequestDTO);
        movementsEntity.setInitialBalance(balanceInitial);
        movementsEntity.setBalance(updatedBalance);
        movementsRepository.save(movementsEntity);

        return new ResponseDTO(Messages.MOVEMENT_CREATED, "00", new Date());
    }
}
