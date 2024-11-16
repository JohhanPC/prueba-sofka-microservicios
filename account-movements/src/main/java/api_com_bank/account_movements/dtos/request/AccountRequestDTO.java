package api_com_bank.account_movements.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRequestDTO {

    private String accountNumber;
    private String accountType;
    private Double balance;
    private boolean state;
    private String identificationNumber;

}
