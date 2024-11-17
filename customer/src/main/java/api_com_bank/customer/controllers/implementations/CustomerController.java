package api_com_bank.customer.controllers.implementations;

import api_com_bank.customer.controllers.contracts.ICustomerController;
import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.CustomerResponseDTO;
import api_com_bank.customer.dtos.response.ResponseDTO;
import api_com_bank.customer.services.contracts.ICustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements ICustomerController {

    private final ICustomerServices customerServices;

    @Override
    public ResponseEntity<ResponseDTO> create(CustomerRequestDTO customerRequestDTO) {
        ResponseDTO response = customerServices.create(customerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> update(CustomerRequestDTO customerRequestDTO) {
        ResponseDTO response = customerServices.update(customerRequestDTO);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<ResponseDTO> delete(String customerId){
        ResponseDTO response = customerServices.delete(customerId);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<CustomerResponseDTO> findByIdentificationNumber(String identificationNumber) {
        CustomerResponseDTO customer = customerServices.findByIdentificationNumber(identificationNumber);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
