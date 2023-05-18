package com.example.mscompte.Repositories;

import com.example.mscompte.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployerRepository extends JpaRepository<Employer,Long>, JpaSpecificationExecutor<Employer> {
}
