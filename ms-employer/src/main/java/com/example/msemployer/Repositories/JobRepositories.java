package com.example.msemployer.Repositories;

import com.example.msemployer.entities.Adresse;
import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Employer;
import com.example.msemployer.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface JobRepositories extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {

   public Iterable<Job> findByEmployer(Employer employer);

   public List<Job> findJobByEmployerIdEmployer(Long employerId);

   public List<Job> findJobByTitle(String title);



    public List<Job> findJobByJobSkills(String jobSkills);


    @Query("Select d.idDemande from Demande d,Job j where d.jobs.idJob=:idJob ")
    public List<Long> findIdDemandesByJobId(Long idJob);


    @Query("select j from Job j where j.Salary>=:minSalary and j.Salary<=:maxSalary")
    public List<Job> findJobBySalaryBetween(Double minSalary,Double maxSalary);

    @Query("select j from Job j where j.location.ville=:ville")
    public List<Job> findJobByLocationVille(String  ville);

    @Query("select j from Job j where j.Salary>=:theSalary")
    public List<Job> findJobBySalaryGreaterThan(Double theSalary);

    @Query("select j from Job j where j.Duration<=:minDuration")
    public List<Job> findJobByDurationLess(String minDuration);

    @Query("select j from Job j where j.Duration>=:maxDuration")
    public List<Job> findJobByDurationMore(String maxDuration);

    @Query("select j from Job j where j.Duration>=:minDuration and j.Duration<=:maxDuration")
    public List<Job> findJobByDurationBetween(String minDuration,String maxDuration);




}
