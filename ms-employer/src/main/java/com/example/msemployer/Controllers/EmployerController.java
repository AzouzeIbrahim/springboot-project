package com.example.msemployer.Controllers;


import com.example.msemployer.Service.EmployerConsumerService;
import com.example.msemployer.entities.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employers")
@CrossOrigin
public class EmployerController {

    @Autowired
    private EmployerConsumerService employerConsumerService;

    @GetMapping("/all")
    public Iterable<Employer> getEmployers() {
        return employerConsumerService.getAllEmployers();
    }


}
