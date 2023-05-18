package com.example.mscompte.services;

import com.example.mscompte.Repositories.EmployerRepository;
import com.example.mscompte.dto.EmployerEvent;
import com.example.mscompte.entity.Employer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class EmployerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerService.class);
    @Autowired
    private EmployerRepository employeeRepository;


    private NewTopic topic;
    private KafkaTemplate<String, EmployerEvent> kafkaTemplate;

    public EmployerService(NewTopic topic, KafkaTemplate<String, EmployerEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(EmployerEvent employerEvent){
//        kafkaTemplate.send(topic.name(),employerEvent);
        LOGGER.info(String.format("EmployerEvent => %s", employerEvent.toString()));
        Message<EmployerEvent> message = MessageBuilder
                .withPayload(employerEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }

    public Iterable<Employer> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employer getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employer createEmployee(Employer employee) {
        Employer employer = Employer.builder()
                .name(employee.getName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .company(employee.getCompany())
                .address(employee.getAddress())
                .postalCode(employee.getPostalCode())
                .phoneNumber(employee.getPhoneNumber())
                .fax(employee.getFax())
                .commercialRegisterNumber(employee.getCommercialRegisterNumber())
                .build();
        return employeeRepository.save(employer);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employer updateEmployer(Long id,Employer employer){
        Employer employer1 = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employer not found"));
        employer1.setName(employer.getName());
        employer1.setLastName(employer.getLastName());
        employer1.setEmail(employer.getEmail());
        employer1.setCompany(employer.getCompany());
        employer1.setAddress(employer.getAddress());
        employer1.setPostalCode(employer.getPostalCode());
        employer1.setPhoneNumber(employer.getPhoneNumber());
        employer1.setFax(employer.getFax());
        employer1.setCommercialRegisterNumber(employer.getCommercialRegisterNumber());
        return employeeRepository.save(employer1);
    }




}
