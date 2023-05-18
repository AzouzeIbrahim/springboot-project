package com.example.msemployer.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private String city;

    private int postalCode;
    private int phoneNumber;

    private int fax;

    private int commercialRegisterNumber ;

//    lazy loading
    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)

    private List<Job> jobs;
}
