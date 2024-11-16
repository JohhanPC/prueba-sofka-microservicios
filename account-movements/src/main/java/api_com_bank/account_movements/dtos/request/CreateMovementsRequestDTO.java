package api_com_bank.account_movements.dtos.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateMovementsRequestDTO {

    private Date date;
    private String movementType;
    private Double value;
    private Double balance;
    private String accountNumber;

}
