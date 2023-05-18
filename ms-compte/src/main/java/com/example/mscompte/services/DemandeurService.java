package com.example.mscompte.services;

import com.example.mscompte.dto.DemandeurEvent;
import com.example.mscompte.entity.Adresse;
import com.example.mscompte.entity.Demandeur;
import com.example.mscompte.Repositories.DemandeurRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeurService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeurService.class);
    @Autowired
    private DemandeurRepository demandeurRepository;


    @Autowired
    private NewTopic topicsecond;
    private KafkaTemplate<String, DemandeurEvent> kafkaTemplate;

    public DemandeurService(NewTopic topic, KafkaTemplate<String, DemandeurEvent> kafkaTemplate) {
        this.topicsecond = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(DemandeurEvent demandeurEvent){
        LOGGER.info(String.format("DemandeurEvent => %s", demandeurEvent.toString()));
        Message<DemandeurEvent> message = MessageBuilder
                .withPayload(demandeurEvent)
                .setHeader(KafkaHeaders.TOPIC, topicsecond.name())
                .build();
        kafkaTemplate.send(message);
    }


    public ResponseEntity<Object> creationDemandeur(Demandeur body){
        try {
            Demandeur demandeur = new Demandeur();
            demandeur.setNom(body.getNom());
            demandeur.setPrenom(body.getPrenom());
            demandeur.setSexe(body.getSexe());
            demandeur.setDateDeNaissance(body.getDateDeNaissance());
            demandeur.setLieuDeNaissance(body.getLieuDeNaissance());
            Adresse adresse = new Adresse();
            adresse.setPays(body.getAdresse().getPays());
            adresse.setVille(body.getAdresse().getVille());
            adresse.setRue(body.getAdresse().getRue());
            demandeur.setAdresse(adresse);
            demandeur.setCodePostal(body.getCodePostal());
            demandeur.setNumeroTel(body.getNumeroTel());
            demandeur.setNiveauEtudes(body.getNiveauEtudes());
            demandeur.setSituationFamiliale(body.getSituationFamiliale());
            demandeur.setNombreEnfants(body.getNombreEnfants());
            demandeur.setHandicap(body.getHandicap());
            demandeur.setLangues(body.getLangues());
            demandeur.setCompetences(body.getCompetences());
            demandeur.setExperienceProfessionnelles(body.getExperienceProfessionnelles());
            demandeurRepository.save(demandeur);
            return new ResponseEntity<>("demandeur inséré avec succès ", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur est survenue lors de l'insertion", HttpStatus.BAD_REQUEST);
        }
    }

    public Object getdemandeurById(Long id) {
        return  demandeurRepository.findById(id).get();
    }

    public List<Demandeur> getDemandeurs() {
        return demandeurRepository.findAll();
    }

    public ResponseEntity<Object> updateDemandeur(Long id , Demandeur body){
        if(demandeurRepository.existsById(id) == false){
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
        Demandeur demandeur = demandeurRepository.findById(id).get();
        demandeur.setNom(body.getNom());
        demandeur.setPrenom(body.getPrenom());
        demandeur.setNiveauEtudes(body.getNiveauEtudes());
        demandeur.setSexe(body.getSexe());
        demandeur.setAdresse(body.getAdresse());
        demandeur.setCodePostal(body.getCodePostal());
        demandeur.setNumeroTel(body.getNumeroTel());
        demandeur.setNationalite(body.getNationalite());
        demandeur.setNumeroCarteIdentite(body.getNumeroCarteIdentite());
        demandeur.setSituationFamiliale(body.getSituationFamiliale());
        demandeur.setNombreEnfants(body.getNombreEnfants());
        demandeur.setHandicap(body.getHandicap());
        demandeurRepository.save(demandeur);
        return new ResponseEntity<>("demandeur mis à jour", HttpStatus.OK);
    }

    public void deleteDemandeur(Long id) {
        demandeurRepository.deleteById(id);
    }

    public List<Demandeur> findByCompetences(String competences) {
       return   demandeurRepository.findByCompetences(competences);
    }


}
