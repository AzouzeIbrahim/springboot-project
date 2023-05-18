package com.example.msemployer.Controllers;


import com.example.msemployer.Service.EmployerConsumerService;
import com.example.msemployer.Service.JobService;
import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Employer;
import com.example.msemployer.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Jobs")
@CrossOrigin
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public Iterable<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    //not working

    @GetMapping("/employer/{employerId}")
    public Iterable<Job> getJobsByEmployer(@PathVariable Long employerId) {
        return jobService.getJobsByEmployer(employerId);
    }

    @GetMapping("/{id}/demandes")
    public ResponseEntity<List<Demande>> getDemandesByJobId(@PathVariable Long id) {
        List<Demande> demandes = jobService.getDemandesByJobId(id);
        if (demandes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/{jobId}")
    public Job getJobById(@PathVariable Long jobId) {
        return jobService.getJobById(jobId);
    }

    @PostMapping("/employer/{employerId}")
    public Job createJob(@RequestBody Job job, @PathVariable Long employerId) {
        return jobService.createJob(job, employerId);
    }

    @PutMapping("/{jobId}")
    public Job updateJob(@RequestBody Job job, @PathVariable Long jobId) {
        return jobService.updateJob(jobId, job);
    }

    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
    }

    @DeleteMapping("/all")
    public void deleteAllJobs() {
        jobService.deleteAllJobs();
    }

    @GetMapping("/job/{title}")
    public List<Job> getJobByTitle(@PathVariable String title) {
        return jobService.getJobByTitle(title);
    }

//    @GetMapping("/job/salary/{salary}")
//    public List<Job> getJobBySalary(@PathVariable double salary) {
//        return jobService.getJobBySalary(salary);
//    }

    @GetMapping("/skills/{jobSkills}")
    public List<Job> getJobByJobSkills(@PathVariable String jobSkills) {
        return jobService.getJobBySkills(jobSkills);
    }


    @GetMapping("/job/{ville}")
    public List<Job> getJobByLocation(@PathVariable String ville) {
        return jobService.getJobByLocation(ville);
    }

    @GetMapping("/job/betweensalary/{minSalary}/{maxSalary}")
    public List<Job> getJobBySalaryBetween(@PathVariable double minSalary, @PathVariable double maxSalary) {
        return jobService.getJobBySalary(minSalary, maxSalary);
    }

    @GetMapping("/job/salary/{minSalary}")
    public List<Job> getJobBySalaryGreaterThan(@PathVariable double minSalary) {
        return jobService.getJobBySalaryGreaterThan(minSalary);
    }

    @GetMapping("/job/maxduree/{maxDuration}")
    public List<Job> getJobByDurationLessThan(@PathVariable String maxDuration) {
        return jobService.findJobByDurationLess(maxDuration);
    }

    @GetMapping("/job/minduree/{minDuration}")
    public List<Job> getJobByDurationGreaterThan(@PathVariable String minDuration) {
        return jobService.findJobByDurationGreaterThan(minDuration);
    }

    @GetMapping("/job/duree/{minDuration}/{maxDuration}")
    public List<Job> getJobByDurationBetween(@PathVariable String minDuration, @PathVariable String maxDuration) {
        return jobService.findJobByDurationBetween(minDuration, maxDuration);
    }


}
