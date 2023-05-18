package com.example.msemployer.Service;

import com.example.mscompte.dto.EmployerEvent;
import com.example.msemployer.Repositories.EmployerRepository;
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

    @KafkaListener(topics = "employer-topic", groupId = "employer")
    public void consume(EmployerEvent employerEvent) {
        LOGGER.info(String.format("Employer event recieved in employer service => %s", employerEvent.toString()));
        //We are saving the employer event into the employer entity in the database so we can have same info
        Employer employer = new Employer();
        employer.setIdEmployer(employerEvent.getEmployer().getIdEmployer());
        employer.setName(employerEvent.getEmployer().getName());
        employer.setLastName(employerEvent.getEmployer().getLastName());
//        employer.setAddress(employerEvent.getEmployer().getAddress());
//        employer.setCity(employerEvent.getEmployer().getCity());
        employer.setEmail(employerEvent.getEmployer().getEmail());
        employer.setCompany(employerEvent.getEmployer().getCompany());
        employer.setPostalCode(employerEvent.getEmployer().getPostalCode());
        employer.setPhoneNumber(employerEvent.getEmployer().getPhoneNumber());
        employer.setFax(employerEvent.getEmployer().getFax());
        employer.setCommercialRegisterNumber(employerEvent.getEmployer().getCommercialRegisterNumber());
        jobService.updateEmployerInJobs(employerEvent.getEmployer().getIdEmployer(), employerEvent.getEmployer().getName());
        employerRepository.save(employer);

    }

    public Iterable<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }


}
