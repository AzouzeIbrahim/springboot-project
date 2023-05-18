package com.example.mscompte.entity;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Adresse {
    private String pays;

    private String ville;

    private String rue;
}
