package com.example.msjobseeker.Repositories;


import com.example.msjobseeker.entities.ExperienceProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {
    @Query("SELECT e FROM ExperienceProfessionnelle e WHERE e.demandeur.idDemandeur=:id")
    public List<ExperienceProfessionnelle> findAllByDemandeurId(Long id);
}
