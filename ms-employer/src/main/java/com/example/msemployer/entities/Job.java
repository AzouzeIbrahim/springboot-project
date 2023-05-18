package com.example.msemployer.entities;


import com.example.msemployer.Enums.CompetenceNom;
import com.example.msemployer.Enums.Gender;
import com.example.msemployer.Enums.Situation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Job {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idJob;
        private String title;
        private String description;
        private Adresse location;
        private int minAge;
        private int maxAge;

        private Gender sexe;

        private Date jobPublishedDate;
        private Date jobExpiredDate;

        private String EducationLevel;

        private Situation situation;

        private String jobTime;

        private Boolean teamWork;



        private String Languages;

        private double Salary;

        private String Duration;
        private String company;
        private String companySize;

        @Enumerated(EnumType.STRING)
        private CompetenceNom jobSkills;

        @ManyToOne
        @JoinColumn(name = "idEmployer")
        @JsonIgnore
        private Employer employer;



        @OneToMany
        @JsonIgnore
        private List<Demande> demandes;


}
