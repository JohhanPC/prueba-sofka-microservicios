package api_com_bank.account_movements.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String documentType;
    private String identificationNumber;
    private String address;
    private String phoneNumber;
    private String customerId;
    private Boolean state;

}
