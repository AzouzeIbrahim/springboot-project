package com.example.msjobseeker.entities;


import com.example.msjobseeker.enums.CompetenceNom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
