package api_com_bank.account_movements.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT_ENTITY")
public class AccountEntity {

    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Column(name = "STATE", nullable = false)
    private boolean state;

    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
    private String identificationNumber;

}
