package api_com_bank.customer.repositories;

import api_com_bank.customer.entities.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveAndFetchCustomer() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setName("Andres Perez");
        customer.setGender("Masculino");
        customer.setAge(30);
        customer.setDocumentType("CC");
        customer.setIdentificationNumber("12345");
        customer.setAddress("Calle 123");
        customer.setPhoneNumber("987654321");
        customer.setPassword("secure_password");
        customer.setState(true);

        // Act
        customerRepository.save(customer);
        var retrieved = customerRepository.findByIdentificationNumber("12345");

        // Assert
        assertTrue(retrieved.isPresent(), "Customer should be present");
        assertEquals("Andres Perez", retrieved.get().getName(), "Name should match");
        assertEquals("secure_password", retrieved.get().getPassword(), "Password should match");
    }

}
