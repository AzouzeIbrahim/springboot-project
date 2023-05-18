package com.example.mscompte.dto;


import com.example.mscompte.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerEvent {
    private String message;
    private String status;
    private Employer employer;
}
