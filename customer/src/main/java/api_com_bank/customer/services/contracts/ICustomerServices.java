package api_com_bank.customer.services.contracts;

import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.CustomerResponseDTO;
import api_com_bank.customer.dtos.response.ResponseDTO;

public interface ICustomerServices {

    ResponseDTO create(CustomerRequestDTO customerRequestDTO);

    ResponseDTO update(CustomerRequestDTO customerRequestDTO);

    ResponseDTO delete(String identificationNumber);

    CustomerResponseDTO findById(String identificationNumber);

}
