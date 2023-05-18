package com.example.mscompte.Repositories;

import com.example.mscompte.entity.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface DemandeurRepository extends JpaRepository<Demandeur,Long>{

    List<Demandeur> findByCompetences(String competences);

}
