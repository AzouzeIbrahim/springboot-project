package com.example.msjobseeker.entities;


import com.example.msjobseeker.enums.Civilite;
import com.example.msjobseeker.enums.Handicap;
import com.example.msjobseeker.enums.Sexe;
import com.example.msjobseeker.enums.SituationFamiliale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demandeurs")
public class Demandeur {
    @Id
    private Long idDemandeur;
    private String nom;
    private String prenom;
    private String email;

//    @Enumerated(EnumType.STRING)
    private String sexe;
    private Date dateDeNaissance;
    private String lieuDeNaissance;

    private String adresse;
    private String codePostal;
    private String numeroTel;
    private String nationalite ;
    private Number numeroCarteIdentite;
    @Enumerated(EnumType.STRING)
    private SituationFamiliale situationFamiliale;
    private Number nombreEnfants;
    @Enumerated(EnumType.STRING)
    private Handicap handicap;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
//    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Competences> competences;


    private String niveauEtudes;

    @ElementCollection
    private List<String> langues;


    @ElementCollection
    private List<String> competences;


    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ExperienceProfessionnelle> experienceProfessionnelles;


//    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<DemandeEmploi> demandeEmplois;


//    public void setSexe(com.example.mscompte.enums.Sexe sexe) {
//    }
//




}
