package api_com_bank.account_movements.dtos.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateMovementsRequestDTO {

    private Long id;
    private Date date;
    private String movementType;
    private double initialBalance;
    private Double value;
    private Double balance;
    private String accountNumber;
}
