package api_com_bank.account_movements.repositories;

import api_com_bank.account_movements.entities.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovementsRepository extends JpaRepository<MovementsEntity, Long> {

    @Query("SELECT m FROM MovementsEntity m " +
            "WHERE m.accountNumber IN " +
            "(SELECT a.accountNumber FROM AccountEntity a WHERE a.identificationNumber = :clientId) " +
            "AND m.date BETWEEN :startDate AND :endDate")
    List<MovementsEntity> findMovementsByClientAndDateRange(
            @Param("clientId") String clientId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}
