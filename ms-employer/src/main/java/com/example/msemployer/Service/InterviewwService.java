package com.example.msemployer.Service;

import com.example.msemployer.Repositories.DemandeRepository;
import com.example.msemployer.Repositories.InterviewRepository;
import com.example.msemployer.entities.Intervieww;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InterviewwService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private DemandeService demandeService;


    @Autowired
    DemandeRepository demandeRepository;



    public Intervieww createInterview(Intervieww intervieww, Long demandeId,Long IdDemande) {
        intervieww.setDemande(demandeService.getDemandeById(demandeId));
        intervieww.setDemande(demandeService.getDemandeById(demandeId));
        return interviewRepository.save(intervieww);

    }

    public Intervieww getInterviewById(Long id) {
        return interviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Interview not found"));
    }

    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }

    public Intervieww updateInterview(Long id, Intervieww intervieww) {
        Intervieww intervieww1 = interviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Interview not found"));
        intervieww1.setDate(intervieww.getDate());
        intervieww1.setDuration(intervieww.getDuration());
        intervieww1.setInterviewResult(intervieww.getInterviewResult());
        intervieww1.setInterviewType(intervieww.getInterviewType());
        intervieww1.setLocation(intervieww.getLocation());
        intervieww1.setStatus(intervieww.getStatus());
        intervieww1.setTime(intervieww.getTime());
        return interviewRepository.save(intervieww1);
    }

    public Iterable<Intervieww> getAllInterviews() {
        return interviewRepository.findAll();
    }

    public void deleteAllInterviews() {
        interviewRepository.deleteAll();
    }

    public Intervieww getInterviewsByDemandeId(Long demandeId) {
        return interviewRepository.findInterviewByDemandeId(demandeId);
    }

}
