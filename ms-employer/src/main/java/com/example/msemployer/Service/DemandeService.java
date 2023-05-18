package com.example.msemployer.Service;


import com.example.msemployer.Repositories.DemandeRepository;
import com.example.msemployer.Repositories.JobRepositories;
import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Job;
import com.example.msjobseeker.Repositories.DemandeEmploiRepository;
import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.dto.DemandeEvent;
import com.example.msjobseeker.entities.DemandeEmploi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeService {
    @Autowired
    DemandeRepository demandeRepository;

    @Autowired
    JobRepositories jobRepository;






    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeService.class);

    public Iterable<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @KafkaListener(topics = "demande-topic", groupId = "demande")
    public void consume(DemandeEvent demandeEvent) {
        LOGGER.info(String.format("Demande event recieved in demande service => %s", demandeEvent.toString()));

//        String jobSeekerUrl = "http://localhost:8082/demande/dmandeur/" + demandeEvent.getDemandeEmploi().getDemandeur();
//
//        DemandeEvent demandeEvent1 =
//        restTemplate.getForObject(jobSeekerUrl, DemandeEvent.class);

        Demande demande = new Demande();
        demande.setIdDemande(demandeEvent.getDemandeEmploi().getIdDemande());
        demande.setDate(demandeEvent.getDemandeEmploi().getDate());
        demande.setCv(demandeEvent.getDemandeEmploi().getCv());
        demande.setSkills(demandeEvent.getDemandeEmploi().getSkills());
        demande.setLettreDeMotivation(demandeEvent.getDemandeEmploi().getLettreDeMotivation());
        demande.setStatus(demandeEvent.getDemandeEmploi().getStatus());
        //demande.getIdDemande(demandeEmploiRepository.findIddemandeurByIdDemande(demandeEvent.getDemandeEmploi().getIdDemande()));
//        demande.setIdDemandeur(demandeEmploiRepository.findDemandeurByIdDemande(demandeEvent.getDemandeEmploi().getIdDemande()).getIdDemandeur());
//        demande.setIdDemandeur(demandeEvent1.getDemandeEmploi().getIdDemande());
        demandeRepository.save(demande);
    }

    public void createDemandeWithJob(Long idJob, Long iddemande) {
        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + idJob));
        Demande demande = demandeRepository.findById(iddemande)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + iddemande));
        demande.setJobs(job);

        List<Long> demandesid= jobRepository.findIdDemandesByJobId(idJob);
        List<Demande> demandes = demandeRepository.findAllById(demandesid);
        job.setDemandes(demandes);
        jobRepository.save(job);
        demandeRepository.save(demande);
    }




    public void updateDemande(Long id, boolean acceptedForInterview) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        demande.setAcceptedforinterview(acceptedForInterview);
        demandeRepository.save(demande);
    }







    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }



    public boolean shouldInsertInTable(Long id) {
        Demande entity = demandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        return entity.getAcceptedforinterview();
    }

}
