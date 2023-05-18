package com.example.msjobseeker.dto;


import com.example.mscompte.entity.Adresse;
import com.example.msjobseeker.enums.Civilite;
import com.example.msjobseeker.enums.Handicap;
import com.example.msjobseeker.enums.Sexe;
import com.example.msjobseeker.enums.SituationFamiliale;
import lombok.Data;

import java.util.Date;

@Data
public class DemandeurDTO {
    private String nom;
    private String prenom;
    private Sexe sexe;
    private Date dateDeNaissance;
    private String lieuDeNaissance;
    private Adresse adresse;
    private String codePostal;
    private String numeroTel;
    private String nationalite ;
    private Number numeroCarteIdentite;
    private SituationFamiliale situationFamiliale;
    private Number nombreEnfants;
    private Handicap handicap;
    private Civilite civilite;



}
