package com.example.msjobseeker.services;


import com.example.msjobseeker.Repositories.CompetencesRepository;
import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.entities.Competences;
import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.enums.CompetenceNom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompetencesService {
    @Autowired
    DemandeurEmploiRepository demandeurEmploiRepository;
    @Autowired
    CompetencesRepository competencesRepository;
    public ResponseEntity<Object> creationCompetence(Long idDemandeur , Competences body){
        try {
            if(demandeurEmploiRepository.existsById(idDemandeur)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Competences competences = new Competences();
            competences.setNom(body.getNom());

            competences.setDemandeur(demandeurEmploiRepository.findById(idDemandeur).get());
            competencesRepository.save(competences);
            return new ResponseEntity<>("competence crée", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getCompetence(Long id){
        try {
            if(competencesRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Competences competences = competencesRepository.findById(id).get();
            return new ResponseEntity<>(competences, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getCompetencesByIdDemandeur(Long id){
        try {
            if(demandeurEmploiRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            List<Competences> competences = competencesRepository.findAllByDemandeurId(id);
            return new ResponseEntity<>(competences, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Object> getAllDemandeursByCompetence(CompetenceNom competence) {
        try {
            List<Demandeur> demandeurs = demandeurEmploiRepository.findByCompetencesNom(competence);
            if (demandeurs.isEmpty()) {
                return new ResponseEntity<>("Aucun demandeur trouvé avec cette compétence", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(demandeurs, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<Object> deleteCompetence(Long id){
//        try {
//            if(competencesRepository.existsById(id)== false){
//                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
//            }
//            competencesRepository.deleteById(id);
//            return new ResponseEntity<>("experience professionnelle supprimée", HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
//        }
//    }


    @Transactional
    public void deleteByNomCompetence(CompetenceNom nom){
        competencesRepository.deleteByNom(nom);
    }








}
