package api_com_bank.customer.services.implementations;

import api_com_bank.customer.dtos.message.CustomerCreatedMessage;
import api_com_bank.customer.dtos.request.CustomerRequestDTO;
import api_com_bank.customer.dtos.response.ResponseDTO;
import api_com_bank.customer.entities.CustomerEntity;
import api_com_bank.customer.exceptions.ClientErrorException;
import api_com_bank.customer.repositories.CustomerRepository;
import api_com_bank.customer.utils.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServicesTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private HandlerMessageService handlerMessageService;

    @InjectMocks
    private CustomerServices customerServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer_Success() {
        // Arrange
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");
        customerRequestDTO.setIdentificationNumber("12345");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("John Doe");
        customerEntity.setIdentificationNumber("12345");

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        // Act
        ResponseDTO response = customerServices.create(customerRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(Messages.CREATE_CUSTOMER, response.getMessage());
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));

        ArgumentCaptor<CustomerCreatedMessage> captor = ArgumentCaptor.forClass(CustomerCreatedMessage.class);
        verify(handlerMessageService, times(1)).sendMessage(captor.capture());

        CustomerCreatedMessage capturedMessage = captor.getValue();
        assertNotNull(capturedMessage);
        assertEquals("12345", capturedMessage.identificationNumber());
        assertEquals("John Doe", capturedMessage.name());
    }

    @Test
    void testUpdateCustomer_NotFound() {
        // Arrange
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setIdentificationNumber("12345");

        when(customerRepository.findByIdentificationNumber("12345")).thenReturn(Optional.empty());

        // Act & Assert
        ClientErrorException exception = assertThrows(ClientErrorException.class,
                () -> customerServices.update(customerRequestDTO));

        assertEquals(String.format(Messages.CUSTOMER_NO_FOUND, "12345"), exception.getMessage());
        verify(customerRepository, never()).save(any(CustomerEntity.class));
    }
}
