package com.example.msjobseeker.services;


import com.example.mscompte.Repositories.DemandeurRepository;
import com.example.mscompte.dto.DemandeurEvent;
import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.dto.DemandeEvent;
import com.example.msjobseeker.entities.Demandeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeurConsumerService {

    @Autowired
    DemandeurEmploiRepository demandeurRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeurConsumerService.class);
    @KafkaListener(topics = "demandeur-topic", groupId = "demandeur")
    public void consume(DemandeurEvent demandeurEvent) {

        LOGGER.info(String.format("Demandeur event recieved in jobseeker service => %s", demandeurEvent.toString()));
        Demandeur demandeur = new Demandeur();
        demandeur.setIdDemandeur(demandeurEvent.getDemandeur().getIdDemandeur());
        demandeur.setNom(demandeurEvent.getDemandeur().getNom());
        demandeur.setPrenom(demandeurEvent.getDemandeur().getPrenom());
        demandeur.setSexe(demandeurEvent.getDemandeur().getSexe());
        demandeur.setDateDeNaissance(demandeurEvent.getDemandeur().getDateDeNaissance());
        demandeur.setLieuDeNaissance(demandeurEvent.getDemandeur().getLieuDeNaissance());
        demandeur.setAdresse(demandeurEvent.getDemandeur().getAdresse());
        demandeur.setCodePostal(demandeurEvent.getDemandeur().getCodePostal());
        demandeur.setNumeroTel(demandeurEvent.getDemandeur().getNumeroTel());
        demandeur.setNationalite(demandeurEvent.getDemandeur().getNationalite());
        demandeur.setNumeroCarteIdentite(demandeurEvent.getDemandeur().getNumeroCarteIdentite());
//        demandeur.setSituationFamiliale(demandeurEvent.getDemandeur().getSituationFamiliale());
        demandeur.setNombreEnfants(demandeurEvent.getDemandeur().getNombreEnfants());
//        demandeur.setHandicap(demandeurEvent.getDemandeur().getHandicap());
        demandeur.setNombreEnfants(demandeurEvent.getDemandeur().getNombreEnfants());
//        demandeur.setCivilite(demandeurEvent.getDemandeur().getCivilite());
//        demandeur.setCompetences(demandeurEvent.getDemandeur().getCompetences());
//        demandeur.setLangues(demandeurEvent.getDemandeur().getLangues());
        demandeurRepository.save(demandeur);
    }

    public ResponseEntity<Object> getdemandeurById(Long id){
        if(demandeurRepository.existsById(id) == false){
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
        Demandeur demandeur = demandeurRepository.findById(id).get();
        return new ResponseEntity<>("demandeur trouvé", HttpStatus.OK);
    }

    public List<Demandeur> getDemandeurs() {
        return demandeurRepository.findAll();
    }

}
