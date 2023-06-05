package com.example.msjobseeker.dto;


import com.example.msjobseeker.entities.Adresse;
import javax.persistence.*;

import com.example.msjobseeker.enums.Sexe;
import lombok.Data;

import java.util.Date;

@Data
public class CreationDemandeurDTO {
    private Long idDemandeur;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private Date dateDeNaissance;
    private String lieuDeNaissance;
    @Embedded
    private Adresse adresse;
    private String codePostal;
    private String numeroTel;
    private String nationalite ;



}
