package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.dtos.response.AccountResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;

public interface IAccountServices {

    ResponseDTO create(AccountRequestDTO accountRequestDTO);

    ResponseDTO update(AccountRequestDTO accountRequestDTO);

    ResponseDTO delete(String accountNumber);

    AccountResponseDTO getAccount(String accountNumber);

}
