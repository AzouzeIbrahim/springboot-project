package com.example.mscompte.entity;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployer  ;

    private String name;
    private String lastName;
    private String email;
    private String company;
    @Embedded
    private Adresse address;


    private int postalCode;
    private int phoneNumber;

    private int fax;

    private int commercialRegisterNumber ;
}
