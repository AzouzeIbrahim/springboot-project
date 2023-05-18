package com.example.mscompte.controller;

import com.example.mscompte.dto.DemandeurEvent;
import com.example.mscompte.entity.Demandeur;
import com.example.mscompte.services.DemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/demandeurs-emploi")
@CrossOrigin
public class DemandeurController {
    @Autowired
    DemandeurService demandeurService;
    public DemandeurController(DemandeurService demandeurService) {
        this.demandeurService = demandeurService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<Object> creationDemandeur(@RequestBody Demandeur demandeur) {
        demandeur.setIdDemandeur(UUID.randomUUID().getLeastSignificantBits() );
        DemandeurEvent demandeurEvent = new DemandeurEvent();
        demandeurEvent.setDemandeur(demandeur);
        demandeurService.sendMessage(demandeurEvent);
        return demandeurService.creationDemandeur(demandeur);
    }
    @GetMapping("{id}")
    Object getDemandeur(@PathVariable("id") Long id){
        return demandeurService.getdemandeurById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Demandeur>> getDemandeurs() {
        List<Demandeur> demandeurs = demandeurService.getDemandeurs();
        return new ResponseEntity<>(demandeurs, HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<Object> updateDemandeur(@PathVariable("id") Long id , @RequestBody Demandeur body){
        return demandeurService.updateDemandeur(id , body);
    }

    @DeleteMapping("/{id}")
    public void deleteDemandeur(@PathVariable Long id) {
        demandeurService.deleteDemandeur(id);
    }



    @GetMapping("/competences/{comeptence}")
    public List<Demandeur> getDemandeursByCompetence(@PathVariable("comeptence") String competence) {
        return demandeurService.findByCompetences(competence);
    }
}
