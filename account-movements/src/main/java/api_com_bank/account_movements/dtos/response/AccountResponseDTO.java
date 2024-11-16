package api_com_bank.account_movements.dtos.response;

import lombok.Data;

@Data
public class AccountResponseDTO {

    private String accountNumber;
    private String accountType;
    private Double balance;
    private boolean state;
    private String identificationNumber;

}
