package com.example.msemployer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InterviewService {

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public boolean createTableIfRequired(Long entityId) {
//        boolean shouldCreateTable = restTemplate.getForObject(
//                "http://localhost:8080/Demandes/{id}/create-table", Boolean.class, entityId);
//
//        if (shouldCreateTable) {
//            jdbcTemplate.execute("CREATE TABLE Interview (idInterview BIGINT PRIMARY KEY GENERATED , Duartion VARCHAR(25),Date VARCHAR(25),Time VARCHAR(25),Location VARCHAR(25),Status VARCHAR(25)," +
//                    "interviewResult VARCHAR(25),FOREIGN KEY idDemande REFERENCES Demande(idDemande))");
//        }
//
//        return shouldCreateTable;
//    }
//
//
//    public void createInterview( String Duartion, String Date, String Time, String Location, String Status,  Long idDemande) {
//        jdbcTemplate.update("INSERT INTO Interview ( Duartion, Date, Time, Location, Status,  idDemande) VALUES ( ?, ?, ?, ?, ?, ?)",
//                 Duartion, Date, Time, Location, Status, idDemande);
//    }
//
//    public void deleteInterview(Long idInterview) {
//        jdbcTemplate.update("DELETE FROM Interview WHERE idInterview = ?", idInterview);
//    }
//
//    public void deleteAllInterviews() {
//        jdbcTemplate.update("DELETE FROM Interview");
//    }
//
//    public void updateInterview(Long idInterview, String Duartion, String Date, String Time, String Location, String Status, Long idDemande) {
//        jdbcTemplate.update("UPDATE Interview SET Duartion = ?, Date = ?, Time = ?, Location = ?, Status = ?, idDemande = ? WHERE idInterview = ?",
//                Duartion, Date, Time, Location, Status, idDemande, idInterview);
//    }
//
//    public void updateInterviewStatus(Long idInterview, String Status) {
//        jdbcTemplate.update("UPDATE Interview SET Status = ? WHERE idInterview = ?",
//                Status, idInterview);
//    }
//
//    public void updateInterviewResult(Long idInterview, String interviewResult) {
//        jdbcTemplate.update("UPDATE Interview SET interviewResult = ? WHERE idInterview = ?",
//                interviewResult, idInterview);
//    }
//
//
//
//
//
//





}
