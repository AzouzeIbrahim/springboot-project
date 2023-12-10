package com.example.msjobseeker.config;

import com.example.msjobseeker.dto.DemandeurDTO;
import com.example.msjobseeker.services.DemandeurConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class consume {
    private ObjectMapper objectMapper;
    private DemandeurConsumerService demandeurConsumerService;


    private DemandeurDTO demandeurDto;


    public consume(ObjectMapper objectMapper, DemandeurConsumerService demandeurConsumerService) {
        this.objectMapper = objectMapper;
        this.demandeurConsumerService = demandeurConsumerService;
    }


//    @KafkaListener(topics = "employer-topic", groupId = "employer")
//    private void consume(ConsumerRecord<String, String> record){
//
//        String message = record.value();
//        System.out.println("message = " + message);
//
//        try {
//            EmployerDto employerDto = objectMapper.readValue(message, EmployerDto.class);
//            employerConsumerService.createEmployer(employerDto);
//        } catch (JsonProcessingException e) {
//            // Handle the JSON processing exception
//            System.out.println("Error processing JSON: " + e.getMessage());
//        } catch (Exception e) {
//            // Handle other exceptions
//            System.out.println("Error consuming message: " + e.getMessage());
//        }
//
//    }


    @KafkaListener(topics = "demandeur-topic", groupId = "demandeur")
    private void consume(ConsumerRecord<String, DemandeurDTO> record) {
        DemandeurDTO demandeurDto = record.value();
        System.out.println("Received message: " + demandeurDto.toString());
        demandeurConsumerService.createDemandeur(demandeurDto);
    }
}
