package com.example.msjobseeker.Repositories;

import com.example.msjobseeker.entities.DemandeEmploi;
import com.example.msjobseeker.entities.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DemandeEmploiRepository extends JpaRepository<DemandeEmploi,Long>, JpaSpecificationExecutor<DemandeEmploi> {
    List<DemandeEmploi> findAllByDemandeur(Demandeur demandeur);

    @Query("select d.demandeur from DemandeEmploi d where d.idDemande=:idDemande")
    Demandeur findDemandeurByIdDemande(Long idDemande);
}
