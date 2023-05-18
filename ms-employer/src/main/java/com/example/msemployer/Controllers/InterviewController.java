package com.example.msemployer.Controllers;



import com.example.msemployer.Service.InterviewwService;
import com.example.msemployer.entities.Intervieww;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
@CrossOrigin
public class InterviewController {

    @Autowired
    InterviewwService interviewService;
    @PostMapping("/create/{demandeId}/{IdDemande}")
    public Intervieww createInterview(@RequestBody Intervieww intervieww, @PathVariable Long demandeId,@PathVariable Long IdDemande) {
        return interviewService.createInterview(intervieww, demandeId,IdDemande);
    }


    @GetMapping("/{id}")
    public Intervieww getInterviewById(@PathVariable Long id) {
        return interviewService.getInterviewById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
    }

    @PutMapping("/{id}")
    public Intervieww updateInterview(@PathVariable Long id, @RequestBody Intervieww intervieww) {
        return interviewService.updateInterview(id, intervieww);
    }

    @GetMapping("/all")
    public Iterable<Intervieww> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

    @GetMapping("/demande/{demandeId}")
    public Intervieww getInterviewsByDemande(@PathVariable Long demandeId) {
        return interviewService.getInterviewsByDemandeId(demandeId);
    }








}
