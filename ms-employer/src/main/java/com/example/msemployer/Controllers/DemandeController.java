package com.example.msemployer.Controllers;


import com.example.msemployer.Service.DemandeService;
import com.example.msemployer.Service.InterviewwService;
import com.example.msemployer.entities.Demande;
import com.example.msemployer.entities.Intervieww;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/Demandes")
@CrossOrigin
public class DemandeController {


    @Autowired
    DemandeService demandeService;

    @Autowired
    InterviewwService interviewwService;


    @GetMapping("/all")
    public Iterable<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    @GetMapping("/{id}")
    public boolean shouldCreateTable(@PathVariable("id") Long id) {
        return demandeService.shouldInsertInTable(id);
    }


    @PostMapping("/update/{id}")
    public void updateDemande(@PathVariable("id") Long id, @RequestParam("acceptedForInterview") boolean acceptedForInterview) {
        demandeService.updateDemande(id, acceptedForInterview);
    }




    @PostMapping("/create/{idjob}/{iddemande}")
    public void createDemande(@PathVariable("idjob") Long id, @PathVariable("iddemande") Long iddemande) {
        demandeService.createDemandeWithJob(id, iddemande );
    }


//    @PostMapping("/{applicationId}/interviews")
//    public Intervieww createInterview(@PathVariable Long applicationId, @RequestBody Intervieww interview) {
//        Demande application = demandeService.getDemandeById(applicationId);
//        if (application != null && application.getAcceptedforinterview()) {
//            interview.setDemande(application);
//            return interviewwService.createInterview(interview);
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interview cannot be created for this application.");
//        }
//    }

}
