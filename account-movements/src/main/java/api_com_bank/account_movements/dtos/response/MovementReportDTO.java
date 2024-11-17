package api_com_bank.account_movements.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovementReportDTO {

    private String date;
    private String client;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean state;
    private Double movementValue;
    private Double availableBalance;

}
