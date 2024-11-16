package api_com_bank.account_movements.repositories;

import api_com_bank.account_movements.entities.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementsRepository extends JpaRepository<MovementsEntity, Long> {
}
