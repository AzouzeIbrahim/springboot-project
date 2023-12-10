package com.example.msjobseeker.services;


import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.Repositories.DiplomeRepository;
import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.entities.Diplome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomeService {
    @Autowired
    DiplomeRepository diplomeRepository;
    @Autowired
    DemandeurEmploiRepository demandeurEmploiRepository;

    public ResponseEntity<Object> creationDiplome(Diplome body, Long idDemandeur){
        try {
            if( demandeurEmploiRepository.existsById(idDemandeur) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Diplome diplome = new Diplome();
            diplome.setTitre(body.getTitre());
            diplome.setPieceJointe(body.getPieceJointe());
            diplome.setNomEtablissement(body.getNomEtablissement());
            diplome.setDateDebut(body.getDateDebut());
            diplome.setDateFin(body.getDateFin());
            diplome.setDemandeur(demandeurEmploiRepository.findById(idDemandeur).get());
            diplomeRepository.save(diplome);
            return new ResponseEntity<>("diplome ajouté", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getDiplomeById(Diplome body, Long idDiplome){
        try {
            if( diplomeRepository.existsById(idDiplome) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Diplome diplome = diplomeRepository.findById(idDiplome).get();
            return new ResponseEntity<>(diplome, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteDiplome(Long idDiplome){
        try {
            if( diplomeRepository.existsById(idDiplome) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            diplomeRepository.deleteById(idDiplome);
            return new ResponseEntity<>("diplome supprimé", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getDiplomeByIdDemandeur(Long idDemandeur){
        try{
            if(demandeurEmploiRepository.existsById(idDemandeur) == false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Demandeur demandeur = demandeurEmploiRepository.findById(idDemandeur).get();
            List<Diplome> diplomes = diplomeRepository.findAllByDemandeur(demandeur);
            return new ResponseEntity<>(diplomes, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updatediplomeById(Long id , Diplome body){
        try {
            if(diplomeRepository.existsById(id)== false){
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
            Diplome diplome = diplomeRepository.findById(id).get();
            diplome.setPieceJointe(body.getPieceJointe());

            diplomeRepository.save(diplome);
            return new ResponseEntity<>(diplome, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }




}
