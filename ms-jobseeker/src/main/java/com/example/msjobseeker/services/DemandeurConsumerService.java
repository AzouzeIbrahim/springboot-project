package com.example.msjobseeker.services;


import com.example.msjobseeker.Repositories.DemandeurEmploiRepository;
import com.example.msjobseeker.dto.DemandeEvent;
import com.example.msjobseeker.dto.DemandeurDTO;
import com.example.msjobseeker.entities.Demandeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DemandeurConsumerService {

    @Autowired
    DemandeurEmploiRepository demandeurRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeurConsumerService.class);
//    @KafkaListener(topics = "demandeur-topic", groupId = "demandeur")
//    public void consume(DemandeurEvent demandeurEvent) {
//
//        LOGGER.info(String.format("Demandeur event recieved in jobseeker service => %s", demandeurEvent.toString()));
//        Demandeur demandeur = new Demandeur();
//        demandeur.setIdDemandeur(demandeurEvent.getDemandeur().getIdDemandeur());
//        demandeur.setNom(demandeurEvent.getDemandeur().getNom());
//        demandeur.setPrenom(demandeurEvent.getDemandeur().getPrenom());
//        demandeur.setSexe(demandeurEvent.getDemandeur().getSexe());
//        demandeur.setDateDeNaissance(demandeurEvent.getDemandeur().getDateDeNaissance());
//        demandeur.setLieuDeNaissance(demandeurEvent.getDemandeur().getLieuDeNaissance());
//        demandeur.setAdresse(demandeurEvent.getDemandeur().getAdresse());
//        demandeur.setCodePostal(demandeurEvent.getDemandeur().getCodePostal());
//        demandeur.setNumeroTel(demandeurEvent.getDemandeur().getNumeroTel());
//        demandeur.setNationalite(demandeurEvent.getDemandeur().getNationalite());
//        demandeur.setNumeroCarteIdentite(demandeurEvent.getDemandeur().getNumeroCarteIdentite());
////        demandeur.setSituationFamiliale(demandeurEvent.getDemandeur().getSituationFamiliale());
//        demandeur.setNombreEnfants(demandeurEvent.getDemandeur().getNombreEnfants());
////        demandeur.setHandicap(demandeurEvent.getDemandeur().getHandicap());
//        demandeur.setNombreEnfants(demandeurEvent.getDemandeur().getNombreEnfants());
////        demandeur.setCivilite(demandeurEvent.getDemandeur().getCivilite());
////        demandeur.setCompetences(demandeurEvent.getDemandeur().getCompetences());
////        demandeur.setLangues(demandeurEvent.getDemandeur().getLangues());
//        demandeurRepository.save(demandeur);
//    }




    public Demandeur createDemandeur(DemandeurDTO demandeurDTO){
        Demandeur demandeur = new Demandeur();
        demandeur.setIdDemandeur(demandeurDTO.getIdDemandeur());
        demandeur.setNom(demandeurDTO.getNom());
        demandeur.setPrenom(demandeurDTO.getPrenom());
        demandeur.setSexe(demandeurDTO.getGenre());
        demandeur.setEmail(demandeurDTO.getEmail());
        demandeur.setDateDeNaissance(demandeurDTO.getDateNaissance());
        demandeur.setLieuDeNaissance(demandeurDTO.getPlaceNaissance());
        demandeur.setAdresse(demandeurDTO.getAdresse());
        demandeur.setCodePostal(demandeurDTO.getCodePostal());
        demandeur.setNumeroTel(demandeurDTO.getPhone());
        demandeur.setNationalite(demandeurDTO.getNationalite());
        demandeur.setNumeroCarteIdentite(demandeurDTO.getNumeroCarteIdentite());
        demandeur.setSituationFamiliale(demandeurDTO.getSituationFamiliale());
        demandeur.setNombreEnfants(demandeurDTO.getNombreEnfants());
        demandeur.setHandicap(demandeurDTO.getHandicap());
        demandeur.setNombreEnfants(demandeurDTO.getNombreEnfants());
        demandeur.setLangues(demandeurDTO.getLangues());
        demandeur.setCompetences(demandeurDTO.getCompetences());
        demandeur.setNiveauEtudes(demandeurDTO.getNiveauEtudes());
//        demandeur.setExperienceProfessionnelles(demandeurDTO.getExperienceProfessionnelles());


//        demandeur.setCivilite(demandeurDTO.getCivilite());
        return demandeurRepository.save(demandeur);
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




//    update demandeur
public Demandeur updateDemandeur(Long id, Demandeur demandeurDTO) {
    Demandeur demandeur = demandeurRepository.getOne(id);

    if (demandeurDTO.getSexe() != null) {
        demandeur.setSexe(demandeurDTO.getSexe());
    }
    if (demandeurDTO.getDateDeNaissance() != null) {
        demandeur.setDateDeNaissance(demandeurDTO.getDateDeNaissance());
    }
    if (demandeurDTO.getLieuDeNaissance() != null) {
        demandeur.setLieuDeNaissance(demandeurDTO.getLieuDeNaissance());
    }
    if (demandeurDTO.getAdresse() != null) {
        demandeur.setAdresse(demandeurDTO.getAdresse());
    }
    if (demandeurDTO.getCodePostal() != null) {
        demandeur.setCodePostal(demandeurDTO.getCodePostal());
    }
    if (demandeurDTO.getNumeroTel() != null) {
        demandeur.setNumeroTel(demandeurDTO.getNumeroTel());
    }
    if (demandeurDTO.getNationalite() != null) {
        demandeur.setNationalite(demandeurDTO.getNationalite());
    }
    if (demandeurDTO.getNumeroCarteIdentite() != null) {
        demandeur.setNumeroCarteIdentite(demandeurDTO.getNumeroCarteIdentite());
    }
    if (demandeurDTO.getSituationFamiliale() != null) {
        demandeur.setSituationFamiliale(demandeurDTO.getSituationFamiliale());
    }
    if (demandeurDTO.getNombreEnfants() != null) {
        demandeur.setNombreEnfants(demandeurDTO.getNombreEnfants());
    }
    if (demandeurDTO.getHandicap() != null) {
        demandeur.setHandicap(demandeurDTO.getHandicap());
    }
    if (demandeurDTO.getCompetences() != null) {
        demandeur.setCompetences(demandeurDTO.getCompetences());
    }
    if (demandeurDTO.getLangues() != null) {
        demandeur.setLangues(demandeurDTO.getLangues());
    }
    if (demandeurDTO.getExperienceProfessionnelles() != null) {
        demandeur.setExperienceProfessionnelles(demandeurDTO.getExperienceProfessionnelles());
    }

    return demandeurRepository.save(demandeur);
}



    public ResponseEntity<Object> deleteDemandeur(Long id){
        if(demandeurRepository.existsById(id) == false){
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
        demandeurRepository.deleteById(id);
        return new ResponseEntity<>("demandeur supprimé", HttpStatus.OK);
    }






}
