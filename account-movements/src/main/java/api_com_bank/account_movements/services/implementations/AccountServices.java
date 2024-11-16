package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.dtos.response.AccountResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.entities.AccountEntity;
import api_com_bank.account_movements.exceptions.ClientErrorException;
import api_com_bank.account_movements.mappers.AccountMapper;
import api_com_bank.account_movements.repositories.AccountRepository;
import api_com_bank.account_movements.services.contracts.IAccountServices;
import api_com_bank.account_movements.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServices implements IAccountServices {

    private final AccountRepository accountRepository;

    @Override
    public ResponseDTO create(AccountRequestDTO accountRequestDTO) {
        log.info("Creating account: {}", accountRequestDTO);
        AccountEntity accountEntity = AccountMapper.INSTANCE.toEntity(accountRequestDTO);
        accountRepository.save(accountEntity);

        return new ResponseDTO(Messages.ACCOUNT_CREATED, "00", new Date());
    }

    @Override
    public ResponseDTO update(AccountRequestDTO accountRequestDTO) {
        log.info("Updating account: {}", accountRequestDTO);
        AccountEntity updateAccount = accountRepository.findById(accountRequestDTO.getAccountNumber())
                .orElseThrow(() -> new ClientErrorException(Messages.ACCOUNT_NO_FOUND + accountRequestDTO.getAccountNumber()));

        AccountEntity accountEntity = AccountMapper.INSTANCE.toEntity(accountRequestDTO);
        accountEntity.setAccountNumber(updateAccount.getAccountNumber());
        accountRepository.save(accountEntity);

        return new ResponseDTO(Messages.ACCOUNT_UPDATED, "00", new Date());
    }

    @Override
    public ResponseDTO delete(String accountNumber) {
        AccountEntity deleteAccount = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ClientErrorException(Messages.ACCOUNT_NO_FOUND + accountNumber));
        accountRepository.delete(deleteAccount);
        return new ResponseDTO(Messages.ACCOUNT_DELETED, "00", new Date());
    }

    @Override
    public AccountResponseDTO getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber)
                .map(AccountMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ClientErrorException(Messages.ACCOUNT_NO_FOUND + accountNumber));
    }
}
