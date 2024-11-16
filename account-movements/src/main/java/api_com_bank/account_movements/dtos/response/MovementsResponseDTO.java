package api_com_bank.account_movements.dtos.response;

import api_com_bank.account_movements.entities.AccountEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MovementsResponseDTO {

    private Long id;
    private Date date;
    private String movementType;
    private Double value;
    private Double balance;
    private AccountEntity cuenta;
}
