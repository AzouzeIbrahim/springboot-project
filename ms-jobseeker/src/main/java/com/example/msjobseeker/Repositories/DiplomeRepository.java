package com.example.msjobseeker.Repositories;


import com.example.msjobseeker.entities.Demandeur;
import com.example.msjobseeker.entities.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {
    List<Diplome> findAllByDemandeur(Demandeur demandeur);
}
