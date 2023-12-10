package com.example.msemployer.entities;

import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.enums.CompetenceNom;
import com.example.msjobseeker.enums.StatusDemandeEmploi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Demande {
    @Id
    private long idDemande;
    private Date date;
    private String cv;
    @ElementCollection
    private List<CompetenceNom> skills;
    private String lettreDeMotivation;
    @Enumerated(EnumType.STRING)
    private StatusDemandeEmploi status;

    private Boolean acceptedforinterview;


    private String DemandeurEmail;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Job jobs;


    private Long idjob;





}
