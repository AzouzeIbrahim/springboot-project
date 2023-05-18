package com.example.msjobseeker.dto;


import com.example.msjobseeker.enums.Metier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExperienceProfessionnelleDTO {
    private Date dateDebut ;
    private Date dateFin ;
    private String description ;
    private String titrePoste;
    private String nomEntreprise;
    private Metier metier;
}
