package api_com_bank.customer.repositories;

import api_com_bank.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByIdentificationNumber(String identificationNumber);
}
