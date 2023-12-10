package com.example.msemployer.Repositories;

import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Intervieww;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface InterviewRepository extends JpaRepository<Intervieww,Long>, JpaSpecificationExecutor<Intervieww> {

    @Query("Select i from Intervieww i,Demande d where d.idDemande=:demandeId and i.demande.idDemande=d.idDemande")
    Intervieww findInterviewByDemandeId(Long demandeId);
}
