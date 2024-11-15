package api_com_bank.customer.dtos.response;

import lombok.Data;

@Data
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
