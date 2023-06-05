package com.example.msemployer.Service;



import com.example.msemployer.Enums.CompetenceNom;
import com.example.msemployer.Repositories.DemandeRepository;
import com.example.msemployer.Repositories.EmployerRepository;
import com.example.msemployer.Repositories.JobRepositories;
import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Employer;
import com.example.msemployer.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepositories jobRepositories;
    @Autowired
    EmployerRepository employerRepository;



    public void deleteJob(Long id) {
        jobRepositories.deleteById(id);
    }

    public void deleteAllJobs() {
        jobRepositories.deleteAll();
    }

    public Iterable<Job> getAllJobs() {
        return jobRepositories.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepositories.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }


    public Job createJob(Job job, Long employerId) {
        Employer employer = employerRepository.getOne(employerId);

        job.setEmployer(employer);
        Job jobb = new Job().builder()
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .minAge(job.getMinAge())
                .maxAge(job.getMaxAge())
                .sexe(job.getSexe())
                .jobPublishedDate(job.getJobPublishedDate())
                .jobExpiredDate(job.getJobExpiredDate())
                .EducationLevel(job.getEducationLevel())
                .situation(job.getSituation())
                .jobTime(job.getJobTime())
                .teamWork(job.getTeamWork())
                .Languages(job.getLanguages())
                .Salary(job.getSalary())
                .Duration(job.getDuration())
                .company(job.getCompany())
                .companySize(job.getCompanySize())
                .jobSkills(job.getJobSkills())
                .employer(employer)
                .build();
        return jobRepositories.save(jobb);
    }

    public Iterable<Job> getJobsByEmployer(Long employerId) {
//        Employer employer = employerRepository.getOne(employerId);
        return jobRepositories.findJobByEmployerIdEmployer(employerId);
    }




    public Job updateJob(Long jobId, Job updatedJob) {
        Job job = jobRepositories.getOne(jobId);
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setSalary(updatedJob.getSalary());
        return jobRepositories.save(job);
    }


    public void updateEmployerInJobs(Long employerId, String employerName) {
//        Employer employer = restTemplate.getForObject("http://localhost:8081/employer/{employerId}", Employer.class, employerId);
        Employer employer = employerRepository.getOne(employerId);
        List<Job> jobs = jobRepositories.findJobByEmployerIdEmployer(employerId);
        jobs.forEach(job -> job.setEmployer(employer));
        jobRepositories.saveAll(jobs);
    }


    public List<Demande> getDemandesByJobId(Long idJob) {
        Optional<Job> job = jobRepositories.findById(idJob);
        if (job.isPresent()) {
            return job.get().getDemandes();
        }
        return null;
    }


    public List<Job> getJobByTitle(String title) {
        return jobRepositories.findJobByTitle(title);
    }

    public List<Job> getJobBySkills(String skills) {
        return jobRepositories.findJobByJobSkills(skills);
    }


    public List<Job> getJobByLocation(String ville) {
        return jobRepositories.findJobByLocationVille(ville);
    }

    public List<Job> getJobBySalary(Double minsalary, Double maxsalary) {
        return jobRepositories.findJobBySalaryBetween(minsalary, maxsalary);
    }

    public List<Job> getJobBySalaryGreaterThan(Double minsalary) {
        return jobRepositories.findJobBySalaryGreaterThan(minsalary);
    }

    public List<Job> findJobByDurationLess(String minDuration) {
        return jobRepositories.findJobByDurationLess(minDuration);
    }

    public List<Job> findJobByDurationGreaterThan(String maxDuration) {
        return jobRepositories.findJobByDurationMore(maxDuration);
    }

    public List<Job> findJobByDurationBetween(String minDuration, String maxDuration) {
        return jobRepositories.findJobByDurationBetween(minDuration, maxDuration);
    }


    public Long countJob() {
        return jobRepositories.countJob();
    }

    public Long countjobBycomptencenom(CompetenceNom comptencenom) {
        return jobRepositories.countJobByJobSkills(comptencenom);
    }


}
