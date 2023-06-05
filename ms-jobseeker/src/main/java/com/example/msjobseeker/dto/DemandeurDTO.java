package com.example.msjobseeker.dto;


import com.example.msjobseeker.entities.Competences;
import com.example.msjobseeker.entities.ExperienceProfessionnelle;
import com.example.msjobseeker.enums.Civilite;
import com.example.msjobseeker.enums.Handicap;
import com.example.msjobseeker.enums.Sexe;
import com.example.msjobseeker.enums.SituationFamiliale;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DemandeurDTO {

    private Long idDemandeur;
    private String nom;
    private String prenom;
    private String genre;
    private Date dateNaissance;
    private String email;
    private String placeNaissance;
    private String adresse;
    private String codePostal;
    private String phone;
    private String nationalite ;
    private Number numeroCarteIdentite;
    private SituationFamiliale situationFamiliale;
    private Number nombreEnfants;
    private Handicap handicap;

    private String niveauEtudes;
    @ElementCollection
    private List<String> langues;

    @ElementCollection
    private List<String> competences;

    @ElementCollection
    private List<String> experienceProfessionnelles;


//    private Set<Competences> competences = new HashSet<>();
//    private Set<ExperienceProfessionnelle> experienceProfessionnelles = new HashSet<>();


//    private Civilite civilite;



}
