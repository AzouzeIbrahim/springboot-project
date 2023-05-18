package com.example.mscompte.entity;


import com.example.mscompte.enums.CompetenceNom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competences")
public class Competences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetence;
    @Enumerated
    private CompetenceNom nom;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "idDemandeur", nullable = false)
    private Demandeur demandeur;

}
