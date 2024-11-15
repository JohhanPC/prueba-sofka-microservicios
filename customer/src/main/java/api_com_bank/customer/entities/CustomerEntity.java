package api_com_bank.customer.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends PersonEntity {

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATE", nullable = false)
    private Boolean state;
}
