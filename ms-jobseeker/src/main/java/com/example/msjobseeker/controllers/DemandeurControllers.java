package com.example.msjobseeker.controllers;


import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.services.DemandeurConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandeur")
@CrossOrigin
public class DemandeurControllers {

    @Autowired
    DemandeurConsumerService demandeurEmploiService;

    @GetMapping("{id}")
    ResponseEntity<Object> getDemandeur(@PathVariable("id") Long id){
        return demandeurEmploiService.getdemandeurById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Demandeur>> getDemandeurs() {
        List<Demandeur> demandeurs = demandeurEmploiService.getDemandeurs();
        return new ResponseEntity<>(demandeurs, HttpStatus.OK);
    }




}
