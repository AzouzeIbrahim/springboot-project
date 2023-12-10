package com.example.msjobseeker.Repositories;

import com.example.msjobseeker.enums.CompetenceNom;
import com.example.msjobseeker.entities.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DemandeurEmploiRepository extends JpaRepository<Demandeur, Long> {

//    List<Demandeur> findByCompetencesNom(String competences);

    Demandeur findByIdDemandeur(Long idDemandeur);


}
