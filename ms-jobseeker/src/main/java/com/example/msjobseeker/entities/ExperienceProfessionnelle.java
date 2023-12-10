package com.example.msjobseeker.entities;


import com.example.msjobseeker.enums.Metier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experienceprofessionnelle")
public class ExperienceProfessionnelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperiencePro ;
    private String titrePoste;
    private String nomEntreprise;
    private Metier metier;
    private Date dateDebut ;
    private Date dateFin ;
    private String description ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "idDemandeur", nullable = false)
    private Demandeur demandeur;
}
