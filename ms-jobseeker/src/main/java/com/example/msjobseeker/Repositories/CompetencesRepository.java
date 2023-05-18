package com.example.msjobseeker.Repositories;


import com.example.msjobseeker.entities.Competences;
import com.example.msjobseeker.enums.CompetenceNom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompetencesRepository extends JpaRepository<Competences, Long> {
    @Query("SELECT c FROM Competences c WHERE c.demandeur.idDemandeur=:id")
    public List<Competences> findAllByDemandeurId(Long id);

    @Query("SELECT c.nom FROM Competences c WHERE c.demandeur.idDemandeur=:id")
    public List<CompetenceNom> findAllNomCompetencesByIdDemandeur(Long id);

    Competences findByNom(String nom);

    void deleteByNom(CompetenceNom nom);
}
