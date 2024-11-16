package api_com_bank.account_movements.controllers.contracts;

import api_com_bank.account_movements.dtos.request.AccountRequestDTO;
import api_com_bank.account_movements.dtos.response.AccountResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/accounts")
public interface IAccountController {

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDTO> create(@RequestBody AccountRequestDTO accountRequestDTO);

    @PutMapping(path = "/update")
    ResponseEntity<ResponseDTO> update(@RequestBody AccountRequestDTO accountRequestDTO);

    @DeleteMapping(path = "/delete/{accountNumber}")
    ResponseEntity<ResponseDTO> delete(@PathVariable String accountNumber);

    @GetMapping(path = "/get/{accountNumber}")
    ResponseEntity<AccountResponseDTO> getAccount(@PathVariable String accountNumber);

}
