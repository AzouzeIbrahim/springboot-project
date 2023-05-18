package com.example.mscompte.controller;


import com.example.mscompte.dto.EmployerEvent;
import com.example.mscompte.entity.Employer;
import com.example.mscompte.services.EmployerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }


    @GetMapping
    public Iterable<Employer> getAllEmployees() {
        return employerService.getAllEmployees();
    }

    @PostMapping("/employers")
    public Employer createEmployer(@RequestBody Employer employer) {
        employer.setIdEmployer(UUID.randomUUID().getLeastSignificantBits() );
        EmployerEvent employerEvent = new EmployerEvent();

//        EmployerEvent employerEvent = new EmployerEvent(employer, adresseJson);

        employerEvent.setEmployer(employer);
        employerService.sendMessage(employerEvent);
        return employerService.createEmployee(employer);
    }

    @GetMapping("/{employerId}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long employerId) {
        Employer employer = employerService.getEmployeeById(employerId);
        return ResponseEntity.ok(employer);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employerService.deleteEmployee(id);
    }


    @PostMapping("/update/{id}")
    public Employer updateEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        return employerService.updateEmployer(id, employer);
    }


}
