package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.IAccountController;
import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.dtos.response.AccountResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.IAccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final IAccountServices accountServices;

    @Override
    public ResponseEntity<ResponseDTO> create(AccountRequestDTO accountRequestDTO) {
        ResponseDTO response = accountServices.create(accountRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> update(AccountRequestDTO accountRequestDTO) {
        ResponseDTO response = accountServices.update(accountRequestDTO);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<ResponseDTO> delete(String accountNumber) {
        ResponseDTO response = accountServices.delete(accountNumber);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<AccountResponseDTO> getAccount(String accountNumber) {
        AccountResponseDTO account = accountServices.getAccount(accountNumber);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
