package com.example.msemployer.config;


import com.example.msemployer.Service.DemandeService;
import com.example.msemployer.Service.EmployerConsumerService;
import com.example.msemployer.dto.DemandeDto;
import com.example.msemployer.dto.EmployerDto;
import com.example.msjobseeker.dto.DemandeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;



@Component
@Slf4j
public class consume {
    private ObjectMapper objectMapper;
    private EmployerConsumerService employerConsumerService;
    private DemandeService demandeService;

    private final ModelMapper modelMapper;

    public consume(ObjectMapper objectMapper, EmployerConsumerService employerConsumerService, DemandeService demandeService,ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.employerConsumerService = employerConsumerService;
        this.demandeService = demandeService;
        this.modelMapper = modelMapper;
    }


    @KafkaListener(topics = "employer-topic",groupId = "employer")
    public void listen(String record) throws Exception {

        EmployerDto employeurs = objectMapper.readValue(record, EmployerDto.class);
        EmployerDto  employeur=modelMapper.map(employeurs, EmployerDto .class);
        employerConsumerService.createEmployer(employeur);

        log.info("Message processed successfully: {}", employeur);

    }
//
    @KafkaListener(topics = "demande")
    public void DemandeEmploi(String record) throws Exception {

        DemandeDto applicationDTO = objectMapper.readValue(record, DemandeDto.class);
        DemandeDto application = modelMapper.map(applicationDTO, DemandeDto.class);
        demandeService.createDemande(application);
        log.info("Message processed successfully: {}", application);
    }


//    @KafkaListener(topics = "employer-topic")
//    private void consumeEmployer(ConsumerRecord<String, EmployerDto> record) {
//        EmployerDto employerDto = record.value();
//        System.out.println("Received employer message: " + employerDto.toString());
//
//        try {
//            employerConsumerService.createEmployer(employerDto);
//        } catch (Exception e) {
//            System.out.println("Error consuming employer message: " + e.getMessage());
//        }
//    }
//
//    @KafkaListener(topics = "demande-topic", groupId = "demande")
//    private void consumeDemande(ConsumerRecord<String, DemandeDto> record) {
//        DemandeDto demandeEvent = record.value();
//        System.out.println("Received demande message: " + demandeEvent.toString());
//
//        try {
//            demandeService.createDemande(demandeEvent);
//        } catch (Exception e) {
//            System.out.println("Error consuming demande message: " + e.getMessage());
//        }
//    }
//    @KafkaListener(topics = "demande-topic")
//    private void consumeDemande(ConsumerRecord<String, Object> record) {
//        Object message = record.value();
//        System.out.println("Received demande message: " + message.toString());
//
//        if (message instanceof DemandeEvent) {
//            DemandeEvent demandeEvent = (DemandeEvent) message;
//            try {
//                demandeService.createDemande(demandeEvent);
//            } catch (Exception e) {
//                System.out.println("Error consuming demande message: " + e.getMessage());
//            }
//        } else {
//            System.out.println("Invalid message type received for demande-topic: " + message.getClass());
//        }
//    }





}
