package com.example.msjobseeker.services;


import com.example.msjobseeker.Repositories.CompetencesRepository;
import com.example.msjobseeker.Repositories.DemandeEmploiRepository;
import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.dto.DemandeEvent;
import com.example.msjobseeker.entities.DemandeEmploi;
import com.example.msjobseeker.entities.Demandeur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DemandeEmploiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeEmploiService.class);

    @Autowired
    DemandeEmploiRepository demandeEmploiRepository;

//    private NewTopic topic;
//    private KafkaTemplate<String, DemandeEvent> kafkaTemplate;
//
//    public DemandeEmploiService(NewTopic topic, KafkaTemplate<String, DemandeEvent> kafkaTemplate) {
//        this.topic = topic;
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(DemandeEvent demandeEvent){
////        kafkaTemplate.send(topic.name(),employerEvent);
//        LOGGER.info(String.format("DemandeEvent => %s", demandeEvent.toString()));
//        Message<DemandeEvent> message = MessageBuilder
//                .withPayload(demandeEvent)
//                .setHeader(KafkaHeaders.TOPIC, topic.name())
//                .build();
//        kafkaTemplate.send(message);
//    }


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public DemandeEmploiService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendDemandeEmploi(DemandeEmploi demandeEmploi) {
        try {
            String jobRequestAsMessage = objectMapper.writeValueAsString(demandeEmploi);
            kafkaTemplate.send("demande", jobRequestAsMessage);
            log.info("job request produced {}", jobRequestAsMessage);
            return "message sent";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "failed to send message";
        }
    }

//    public DemandeEmploi createdemande(DemandeEmploi demandee,Long id) {
//        DemandeEmploi demande = DemandeEmploi.builder()
//                .date(demandee.getDate())
//                .derniereExperience(demandee.getDerniereExperience())
//                .anneeDebut(demandee.getAnneeDebut())
//                .anneeFin(demandee.getAnneeFin())
//                .cv(demandee.getCv())
//                .experience(demandee.getExperience())
//                .fonction(demandee.getFonction())
//                .niveauEtudes(demandee.getNiveauEtudes())
//                .niveauEtudes(demandee.getLettreDeMotivation())
//                .build();
//        return demandeEmploiRepository.save(demande);
//    }


    @Autowired
    DemandeurEmploiRepository demandeurEmploiRepository;

    @Autowired
    CompetencesRepository competencesRepository;

    public ResponseEntity<Object> creationDemandeEmploi(DemandeEmploi body, Long idDemandeur){
        try {
            if(demandeurEmploiRepository.existsById(idDemandeur) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            DemandeEmploi demandeEmploi = new DemandeEmploi();
            demandeEmploi.setIdDemande(body.getIdDemande());
            demandeEmploi.setDate(body.getDate());
            demandeEmploi.setCv(body.getCv());
            demandeEmploi.setSkills(body.getSkills());
            demandeEmploi.setLettreDeMotivation(body.getLettreDeMotivation());
            demandeEmploi.setSkills(competencesRepository.findAllNomCompetencesByIdDemandeur(idDemandeur));
            demandeEmploi.setStatus(body.getStatus());
//            demandeEmploi.setDemandeur(demandeurEmploiRepository.findById(idDemandeur).get());
            demandeEmploi.setDemandeur(demandeurEmploiRepository.findByIdDemandeur(idDemandeur));

            demandeEmploiRepository.save(demandeEmploi);
            return new ResponseEntity<>("demande d'emploi crée", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Object> getDemandeEmploiById(Long id){
        try{
            DemandeEmploi demandeEmploi = (DemandeEmploi) demandeEmploiRepository.findById(id).get();
            return new ResponseEntity<>(demandeEmploi, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getDemandesByIdDemandeur(Long id){
        try{
            if(demandeurEmploiRepository.existsById(id) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);

            }
            Demandeur demandeur = demandeurEmploiRepository.findById(id).get();
            List<DemandeEmploi> demandeEmplois = demandeEmploiRepository.findAllByDemandeur(demandeur);
            return new ResponseEntity<>(demandeEmplois, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateDemandeEmploi(Long id , DemandeEmploi body){
        if(demandeEmploiRepository.existsById(id) == false){
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
        DemandeEmploi demandeEmploi = demandeEmploiRepository.findById(id).get();
        demandeEmploi.setStatus(body.getStatus());
        demandeEmploi.setDate(body.getDate());
        demandeEmploi.setLettreDeMotivation(body.getLettreDeMotivation());
        demandeEmploi.setCv(body.getCv());
        return new ResponseEntity<>("demande d'emploi mise à jour", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteDemandeEmploiById(Long id) {
        try{
            demandeEmploiRepository.deleteById(id);
            return new ResponseEntity<>("demande d'emploi supprimée", HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);

        }
    }

    public Demandeur getDemandeurByIdDemande(Long idDemande){
        return demandeEmploiRepository.findDemandeurByIdDemande(idDemande);
    }



}
