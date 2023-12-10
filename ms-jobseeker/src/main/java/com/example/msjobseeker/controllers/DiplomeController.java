package com.example.msjobseeker.controllers;


import com.example.msjobseeker.entities.Diplome;
import com.example.msjobseeker.services.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diplome")
@CrossOrigin
public class DiplomeController {
    @Autowired
    DiplomeService diplomeService;

    @PostMapping("/{idDemandeur}")
    ResponseEntity<Object> creationDiplome(@RequestBody Diplome body, @PathVariable("idDemandeur") Long idDemandeur){
        return diplomeService.creationDiplome(body,idDemandeur);
    }

    @GetMapping("/{idDiplome}")
    ResponseEntity<Object> getDiplomeById(@RequestBody Diplome body, @PathVariable("idDiplome") Long idDiplome){
        return diplomeService.getDiplomeById(body,idDiplome);
    }

    @DeleteMapping("/{idDiplome}")
    ResponseEntity<Object> deleteDiplome(@PathVariable("idDiplome") Long idDiplome){
        return diplomeService.deleteDiplome(idDiplome);
    }

    @GetMapping("/diplomes-par-demandeur/{idDemandeur}")
    ResponseEntity<Object> getDiplomeByIdDemandeur(@PathVariable("idDemandeur") Long idDemandeur){
        return diplomeService.getDiplomeByIdDemandeur(idDemandeur);
    }


    @PutMapping("{id}")
    ResponseEntity<Object> updatediplomeById(@PathVariable("id") Long id, @RequestBody Diplome body){
        return diplomeService.updatediplomeById(id,body);
    }

}
