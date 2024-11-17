package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.messages.AccountCreatedMessage;
import api_com_bank.account_movements.dtos.messages.CustomerCreatedMessage;
import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.entities.AccountEntity;
import api_com_bank.account_movements.mappers.AccountMapper;
import api_com_bank.account_movements.repositories.AccountRepository;
import api_com_bank.account_movements.services.contracts.IAccountInternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountInternalService implements IAccountInternalService {

    private final AccountRepository accountRepository;
    private static final Random RANDOM = new Random();

    @Override
    public AccountCreatedMessage processCustomerMessage(CustomerCreatedMessage customerMessage) {
        log.info("Processing customer message: {}", customerMessage);

        return Optional.of(generateAccountNumber())
                .map(accountNumber -> {
                    AccountEntity newAccount = AccountMapper.INSTANCE.toEntity(
                            buildAccountRequest(customerMessage, accountNumber)
                    );
                    accountRepository.save(newAccount);
                    log.info("Account created and saved to database: {}", newAccount);
                    return buildAccountCreatedMessage(accountNumber, customerMessage);
                })
                .orElseThrow(() -> new RuntimeException("Failed to process customer message"));
    }

    private String generateAccountNumber() {
        return String.format("%09d", RANDOM.nextInt(1_000_000_000));
    }

    private AccountRequestDTO buildAccountRequest(CustomerCreatedMessage customerMessage, String accountNumber) {
        return new AccountRequestDTO(
                accountNumber,
                "Ahorros",
                0.0,
                true,
                customerMessage.identificationNumber()
        );
    }

    private AccountCreatedMessage buildAccountCreatedMessage(String accountNumber, CustomerCreatedMessage customerMessage) {
        return new AccountCreatedMessage(
                extractLastDigits(customerMessage.identificationNumber()),
                extractLastDigits(accountNumber),
                customerMessage.name()

        );
    }

    private String extractLastDigits(String value) {
        return value.substring(value.length() - 4);
    }
}
