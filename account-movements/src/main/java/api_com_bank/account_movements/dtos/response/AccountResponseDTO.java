package api_com_bank.account_movements.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseDTO {

    private String accountNumber;
    private String accountType;
    private Double balance;
    private boolean state;
    private String identificationNumber;

}
