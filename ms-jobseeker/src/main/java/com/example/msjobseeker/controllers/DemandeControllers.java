package com.example.msjobseeker.controllers;


import com.example.msjobseeker.Repositories.DemandeEmploiRepository;
import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.dto.DemandeEvent;
import com.example.msjobseeker.entities.DemandeEmploi;
import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.services.DemandeEmploiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/demande")
@CrossOrigin
public class DemandeControllers {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeControllers.class);

    @Autowired
    private DemandeEmploiService demandeEmploiService;

    @Autowired
    DemandeurEmploiRepository demandeurEmploiRepository;

    @Autowired
    DemandeEmploiRepository demandeEmploiRepository;

    public DemandeControllers(DemandeEmploiService demandeEmploiService) {
        this.demandeEmploiService = demandeEmploiService;
    }

    @PostMapping("/creation/{id}")
        public ResponseEntity<Object> createDemande(@RequestBody DemandeEmploi demandeEmploi, @PathVariable Long id) {
//        demandeEmploi.setIdDemande(UUID.randomUUID().getLeastSignificantBits() );
//        demandeEmploi.setIdDemande(id);
        DemandeEvent demandeEvent = new DemandeEvent();
        demandeEvent.setDemandeEmploi(demandeEmploi);
//        demandeEvent.getDemandeEmploi().setDemandeur(demandeurEmploiRepository.findByIdDemandeur(id));
//        demandeEvent.getDemandeEmploi().setDemandeur(demandeurEmploiRepository.findById(id).get());
//        LOGGER.info(String.format("this looooooooooooooooooooooooooooooog",  demandeEvent.getDemandeEmploi().getDemandeur().getIdDemandeur().toString()));
        demandeEmploiService.sendDemandeEmploi(demandeEmploi);
        return demandeEmploiService.creationDemandeEmploi(demandeEmploi,id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getDemandeEmploiById(@PathVariable("id") Long id){
        if(demandeEmploiRepository.existsById(id) == false){
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
        return demandeEmploiService.getDemandeEmploiById(id);
    }
    @GetMapping("/liste-demandes/{idDemandeur}")
    ResponseEntity<Object> getDemandesByDemandeur(@PathVariable("idDemandeur") Long id){
        return demandeEmploiService.getDemandesByIdDemandeur(id);
    }

    @PutMapping("{id}")
    ResponseEntity<Object> updateDemandeEmploi(@PathVariable("id") Long id , @RequestBody DemandeEmploi body){
        return demandeEmploiService.updateDemandeEmploi(id , body);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteDemandeById(@PathVariable("id") Long id){
        return demandeEmploiService.deleteDemandeEmploiById(id);
    }

    @GetMapping("/dmandeur/{idDemande}")
    Demandeur getDemandeurByIdDemande(@PathVariable("idDemande") Long idDemande){
        return demandeEmploiService.getDemandeurByIdDemande(idDemande);
    }



}
