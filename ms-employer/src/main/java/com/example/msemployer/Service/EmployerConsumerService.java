package com.example.msemployer.Service;


import com.example.msemployer.Repositories.EmployerRepository;
import com.example.msemployer.dto.EmployerDto;
import com.example.msemployer.entities.Employer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployerConsumerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobService jobService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerConsumerService.class);

//    @KafkaListener(topics = "employer-topic", groupId = "employer")
//    public void consume(EmployerEvent employerEvent) {
//        LOGGER.info(String.format("Employer event recieved in employer service => %s", employerEvent.toString()));
//        //We are saving the employer event into the employer entity in the database so we can have same info
//        Employer employer = new Employer();
//        employer.setIdEmployer(employerEvent.getEmployer().getIdEmployer());
//        employer.setName(employerEvent.getEmployer().getName());
//        employer.setLastName(employerEvent.getEmployer().getLastName());
////        employer.setAddress(employerEvent.getEmployer().getAddress());
////        employer.setCity(employerEvent.getEmployer().getCity());
//        employer.setEmail(employerEvent.getEmployer().getEmail());
//        employer.setCompany(employerEvent.getEmployer().getCompany());
//        employer.setPostalCode(employerEvent.getEmployer().getPostalCode());
//        employer.setPhoneNumber(employerEvent.getEmployer().getPhoneNumber());
//        employer.setFax(employerEvent.getEmployer().getFax());
//        employer.setCommercialRegisterNumber(employerEvent.getEmployer().getCommercialRegisterNumber());
//        jobService.updateEmployerInJobs(employerEvent.getEmployer().getIdEmployer(), employerEvent.getEmployer().getName());
//        employerRepository.save(employer);
//
//    }


    public Employer createEmployer(EmployerDto employerdto) {
        Employer employer = new Employer();
        employer.setIdEmployer(employerdto.getIdEmployeur());
        employer.setName(employerdto.getNom());
        employer.setLastName(employerdto.getPrenom());
        employer.setEmail(employerdto.getEmail());
        employer.setCompany(employerdto.getEntreprise());
        employer.setAddress(employerdto.getAdresse());
        employer.setCity(employerdto.getWilaya());
        employer.setPostalCode(employerdto.getPostalCode());
        employer.setPhoneNumber(employerdto.getPhone());
        employer.setFax(employerdto.getFax());
        employer.setCommercialRegisterNumber(employerdto.getNumeroCommerial());


        return employerRepository.save(employer);
    }

    public Iterable<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }



    public Employer updateEmployer(Long id, EmployerDto employerdto) {
        Employer employer = employerRepository.findById(id).get();

        if (employerdto.getEntreprise() != null) {
            employer.setCompany(employerdto.getEntreprise());
        }
        if (employerdto.getAdresse() != null) {
            employer.setAddress(employerdto.getAdresse());
        }
        if (employerdto.getWilaya() != null) {
            employer.setCity(employerdto.getWilaya());
        }
        if (employerdto.getPostalCode() != 0) {
            employer.setPostalCode(employerdto.getPostalCode());
        }
        if (employerdto.getPhone() != 0) {
            employer.setPhoneNumber(employerdto.getPhone());
        }
        if (employerdto.getFax() != 0) {
            employer.setFax(employerdto.getFax());
        }
        if (employerdto.getNumeroCommerial() != 0) {
            employer.setCommercialRegisterNumber(employerdto.getNumeroCommerial());
        }

        return employerRepository.save(employer);
    }


    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }


    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id).get();
    }


}
