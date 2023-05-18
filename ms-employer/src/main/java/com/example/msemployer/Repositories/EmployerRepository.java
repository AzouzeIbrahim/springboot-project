package com.example.msemployer.Repositories;

import com.example.msemployer.entities.Employer;
import com.example.msemployer.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployerRepository extends JpaRepository<Employer,Long>, JpaSpecificationExecutor<Employer> {


}
