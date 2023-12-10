package com.example.msemployer.dto;

import com.example.msjobseeker.enums.CompetenceNom;
import com.example.msjobseeker.enums.StatusDemandeEmploi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandeDto {
   private long idDemande;
   private Date date;
   private String cv;
   @ElementCollection
   private List<CompetenceNom> skills;
   private String lettreDeMotivation;
   @Enumerated(EnumType.STRING)
   private StatusDemandeEmploi status;

   private String DemandeurEmail;

   private Long idjob;

   // Add a constructor that accepts a String argument for deserialization
   public DemandeDto(String json) throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      DemandeDto demandeDto = objectMapper.readValue(json, DemandeDto.class);
      this.idDemande = demandeDto.idDemande;
      this.date = demandeDto.date;
      this.cv = demandeDto.cv;
      this.skills = demandeDto.skills;
      this.lettreDeMotivation = demandeDto.lettreDeMotivation;
      this.status = demandeDto.status;
      this.DemandeurEmail = demandeDto.DemandeurEmail;
      this.idjob = demandeDto.idjob;

   }

}
