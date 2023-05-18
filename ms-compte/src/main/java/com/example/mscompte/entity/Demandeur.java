package com.example.mscompte.entity;

import com.example.mscompte.enums.Civilite;
import com.example.mscompte.enums.Handicap;
import com.example.mscompte.enums.Sexe;
import com.example.mscompte.enums.SituationFamiliale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demandeurscomptes")
public class Demandeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Number numeroCarteIdentite;

    private String niveauEtudes;
    @Enumerated(EnumType.STRING)
    private SituationFamiliale situationFamiliale;
    private Number nombreEnfants;
    @Enumerated(EnumType.STRING)
    private Handicap handicap;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    @ElementCollection
    private List<String> langues;
    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Competences> competences;

//    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<DemandeEmploi> demandeEmplois;

    @OneToMany(mappedBy = "demandeur", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ExperienceProfessionnelle> experienceProfessionnelles;


}
