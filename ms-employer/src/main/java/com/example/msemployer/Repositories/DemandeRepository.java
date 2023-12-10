package com.example.msemployer.Repositories;

import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemandeRepository extends JpaRepository<Demande,Long>, JpaSpecificationExecutor<Demande> {
}
