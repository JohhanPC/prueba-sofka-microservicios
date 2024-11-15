package api_com_bank.customer.dtos.request;

import lombok.Data;

@Data
public class CustomerRequestDTO {

    private String name;
    private String gender;
    private Integer age;
    private String documentType;
    private String identificationNumber;
    private String address;
    private String phoneNumber;
    private String password;
    private Boolean state;
}
