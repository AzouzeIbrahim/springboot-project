package com.example.mscompte.dto;

import com.example.mscompte.entity.Demandeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeurEvent {
    private String message;
    private String status;
    private Demandeur demandeur;

}
