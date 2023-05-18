package com.example.msjobseeker.controllers;


import com.example.msjobseeker.dto.ExperienceProfessionnelleDTO;
import com.example.msjobseeker.entities.ExperienceProfessionnelle;
import com.example.msjobseeker.services.ExperienceProfessionnelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience-professionnelle")
@CrossOrigin
public class ExperienceProfessionnelleController {
    @Autowired
    ExperienceProfessionnelleService experienceProfessionnelleService;

    @PostMapping("{demandeurId}")
    ResponseEntity<Object> creationExperiencePro(@PathVariable("demandeurId") Long demandeurId , @RequestBody ExperienceProfessionnelle body){
        return experienceProfessionnelleService.creationExperiencePro(demandeurId , body);
    }

    @GetMapping("{id}")
    ResponseEntity<Object> getExperienceProById(@PathVariable("id") Long id){
        return experienceProfessionnelleService.getExperiencePro(id);
    }

    @GetMapping("par-demandeur/{id}")
    ResponseEntity<Object> getExperienceProByIdDemandeur(@PathVariable("id") Long id){
        return experienceProfessionnelleService.getExperienceProByIdDemandeur(id);
    }

    @PutMapping("{id}")
    ResponseEntity<Object> updateExperiencePro(@PathVariable("id") Long id, @RequestBody ExperienceProfessionnelleDTO body){
        return experienceProfessionnelleService.updateExperienceProById(id,body);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteExperiencePro(@PathVariable("id") Long id){
        return experienceProfessionnelleService.deleteExperiencePro(id);
    }







}

