package com.example.msemployer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDto {
    private Long idEmployeur;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private String adresse;

    private String wilaya;


    private int postalCode;
    private int phone;

    private int fax;

    private int numeroCommerial;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
