package api_com_bank.account_movements.repositories;

import api_com_bank.account_movements.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
