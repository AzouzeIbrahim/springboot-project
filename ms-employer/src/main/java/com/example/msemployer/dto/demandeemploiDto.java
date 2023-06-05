package com.example.msemployer.dto;


import com.example.msjobseeker.enums.CompetenceNom;
import com.example.msjobseeker.enums.StatusDemandeEmploi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class demandeemploiDto {

    private long idDemande;
    private Date date;
    private String cv;
    @ElementCollection
    private List<CompetenceNom> skills;
    private String lettreDeMotivation;
    @Enumerated(EnumType.STRING)
    private StatusDemandeEmploi status;
}
