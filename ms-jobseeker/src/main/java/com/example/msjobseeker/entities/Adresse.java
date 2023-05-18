package com.example.msjobseeker.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Adresse {
    private String pays;

    private String ville;

    private String rue;
}
