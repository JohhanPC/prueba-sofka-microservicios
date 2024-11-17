package api_com_bank.customer.services.implementations;

import api_com_bank.customer.dtos.message.CustomerCreatedMessage;
import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.CustomerResponseDTO;
import api_com_bank.customer.dtos.response.ResponseDTO;
import api_com_bank.customer.entities.CustomerEntity;
import api_com_bank.customer.exceptions.ClientErrorException;
import api_com_bank.customer.mappers.CustomerMapper;
import api_com_bank.customer.repositories.CustomerRepository;
import api_com_bank.customer.services.contracts.ICustomerServices;
import api_com_bank.customer.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServices implements ICustomerServices {

    private final CustomerRepository customerRepository;
    private final HandlerMessageService handlerMessageService;

    @Override
    public ResponseDTO create(CustomerRequestDTO customerRequestDTO) {
        log.info("Creating customer: {}", customerRequestDTO);
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toEntity(customerRequestDTO);
        customerRepository.save(customerEntity);

        Optional.of(customerRequestDTO)
                .map(dto -> new CustomerCreatedMessage(dto.getIdentificationNumber(), dto.getName()))
                .ifPresent(handlerMessageService::sendMessage);

        return new ResponseDTO(Messages.CREATE_CUSTOMER, "00", new Date());
    }

    @Override
    public ResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        log.info("Updating customer: {}", customerRequestDTO);
        var customerUpdate = customerRepository.findByIdentificationNumber(customerRequestDTO.getIdentificationNumber())
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.CUSTOMER_NO_FOUND, customerRequestDTO.getIdentificationNumber())));

        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toEntity(customerRequestDTO);
        customerEntity.setId(customerUpdate.getId());
        customerRepository.save(customerEntity);

        return new ResponseDTO(Messages.UPDATE_CUSTOMER, "00", new Date());
    }

    @Override
    public ResponseDTO delete(String identificationNumber) {
        log.info("Deleting customer: {}", identificationNumber);
        var customerToDelete = customerRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.CUSTOMER_NO_FOUND, identificationNumber)));

        customerRepository.delete(customerToDelete);
        return new ResponseDTO(Messages.DELETE_CUSTOMER, "00", new Date());
    }

    @Override
    public CustomerResponseDTO findByIdentificationNumber(String identificationNumber) {
        log.info("Finding customer: {}", identificationNumber);
        return customerRepository.findByIdentificationNumber(identificationNumber)
                .map(CustomerMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.CUSTOMER_NO_FOUND, identificationNumber)));
    }
}
