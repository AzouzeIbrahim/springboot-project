package com.example.msjobseeker.dto;

import com.example.msjobseeker.entities.DemandeEmploi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeEvent {
    private String message;
    private String status;
    private DemandeEmploi demandeEmploi;
}
