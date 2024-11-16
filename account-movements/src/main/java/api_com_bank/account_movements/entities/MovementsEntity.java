package api_com_bank.account_movements.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVEMENTS")
public class MovementsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "MOVEMENT_TYPE", nullable = false)
    private String movementType;

    @Column(name = "VALUE", nullable = false)
    private Double value;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

}
