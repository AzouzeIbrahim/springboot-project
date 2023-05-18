package com.example.msjobseeker.services;

import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.Repositories.ExperienceProfessionnelleRepository;
import com.example.msjobseeker.dto.ExperienceProfessionnelleDTO;
import com.example.msjobseeker.entities.ExperienceProfessionnelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceProfessionnelleService {
    @Autowired
    DemandeurEmploiRepository demandeurEmploiRepository;
    @Autowired
    ExperienceProfessionnelleRepository experienceProfessionnelleRepository;


    public ResponseEntity<Object> creationExperiencePro(Long idDemandeur , ExperienceProfessionnelle body){
        try {
            if(demandeurEmploiRepository.existsById(idDemandeur)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
            experienceProfessionnelle.setDateDebut(body.getDateDebut());
            experienceProfessionnelle.setDateFin(body.getDateFin());
            experienceProfessionnelle.setDescription(body.getDescription());
            experienceProfessionnelle.setMetier(body.getMetier());
            experienceProfessionnelle.setNomEntreprise(body.getNomEntreprise());
            experienceProfessionnelle.setTitrePoste(body.getTitrePoste());
            experienceProfessionnelle.setDemandeur(demandeurEmploiRepository.findById(idDemandeur).get());
            experienceProfessionnelleRepository.save(experienceProfessionnelle);
            return new ResponseEntity<>("expreience professionnelle crée", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getExperiencePro(Long id){
        try {
            if(experienceProfessionnelleRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            ExperienceProfessionnelle experienceProfessionnelle = experienceProfessionnelleRepository.findById(id).get();
            return new ResponseEntity<>(experienceProfessionnelle, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getExperienceProByIdDemandeur(Long id){
        try {
            if(demandeurEmploiRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            List<ExperienceProfessionnelle> experienceProfessionnelles = experienceProfessionnelleRepository.findAllByDemandeurId(id);
            return new ResponseEntity<>(experienceProfessionnelles, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateExperienceProById(Long id , ExperienceProfessionnelleDTO body){
        try {
            if(experienceProfessionnelleRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            ExperienceProfessionnelle experienceProfessionnelle = experienceProfessionnelleRepository.findById(id).get();
            experienceProfessionnelle.setDateDebut(body.getDateDebut());
            experienceProfessionnelle.setDateFin(body.getDateFin());
            experienceProfessionnelle.setDescription(body.getDescription());
            experienceProfessionnelle.setTitrePoste(body.getTitrePoste());
            experienceProfessionnelle.setNomEntreprise(body.getNomEntreprise());
            experienceProfessionnelle.setMetier(body.getMetier());
            experienceProfessionnelleRepository.save(experienceProfessionnelle);
            return new ResponseEntity<>(experienceProfessionnelle, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteExperiencePro(Long id){
        try {
            if(experienceProfessionnelleRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            experienceProfessionnelleRepository.deleteById(id);
            return new ResponseEntity<>("experience professionnelle supprimée", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }





}
