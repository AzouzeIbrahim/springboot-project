package com.example.msjobseeker.controllers;


import com.example.msjobseeker.entities.Competences;
import com.example.msjobseeker.enums.CompetenceNom;
import com.example.msjobseeker.services.CompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competences")
@CrossOrigin
public class CompetencesController {
    @Autowired
    CompetencesService competencesService;

    @PostMapping("{demandeurId}")
    ResponseEntity<Object> creationCompetence(@PathVariable("demandeurId") Long demandeurId , @RequestBody Competences body){
        return competencesService.creationCompetence(demandeurId , body);
    }
    @GetMapping("{id}")
    ResponseEntity<Object> getCompetenceById(@PathVariable("id") Long id){
        return competencesService.getCompetence(id);
    }
    @GetMapping("par-demandeur/{id}")
    ResponseEntity<Object> getCompetencesByIdDemandeur(@PathVariable("id") Long id){
        return competencesService.getCompetencesByIdDemandeur(id);
    }

    @GetMapping("demandeurs-par-competence/{competence}")
    ResponseEntity<Object> getAllDemandeursByCompetence(@PathVariable("competence") CompetenceNom competence){
        return competencesService.getAllDemandeursByCompetence(competence);
    }

    @DeleteMapping("/{nom}")
    public ResponseEntity<Void> deleteByNomCompetence(@PathVariable CompetenceNom nom) {
        competencesService.deleteByNomCompetence(nom);
        return ResponseEntity.noContent().build();
    }







}
