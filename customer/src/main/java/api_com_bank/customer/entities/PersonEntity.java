package api_com_bank.customer.entities;

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
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "GENDER",nullable = false)
    private String gender;

    @Column(name = "AGE",nullable = false)
    private Integer age;

    @Column(name = "DOCUMENT_TYPE")
    private String documentType;

    @Column(name = "IDENTIFICATION_NUMBER", nullable = false, unique = true)
    private String identificationNumber;

    @Column(name = "ADDRESS",nullable = false)
    private String address;

    @Column(name = "PHONE_NUMBER",nullable = false)
    private String phoneNumber;

}
