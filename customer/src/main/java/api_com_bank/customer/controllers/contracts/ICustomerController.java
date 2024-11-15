package api_com_bank.customer.controllers.contracts;

import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.CustomerResponseDTO;
import api_com_bank.customer.dtos.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/customers")
public interface ICustomerController {

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDTO> create(@RequestBody CustomerRequestDTO customerRequestDTO);

    @PutMapping(path = "/update")
    ResponseEntity<ResponseDTO> update(@RequestBody CustomerRequestDTO customerRequestDTO);

    @DeleteMapping(path = "/delete/{customerId}")
    ResponseEntity<ResponseDTO> delete(@PathVariable String customerId);

    @GetMapping(path = "/findById/{customerId}")
    ResponseEntity<CustomerResponseDTO> findById(@PathVariable String customerId);

}
