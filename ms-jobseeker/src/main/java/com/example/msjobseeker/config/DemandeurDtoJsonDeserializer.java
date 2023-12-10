package com.example.msjobseeker.config;

import com.example.msjobseeker.dto.DemandeurDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class DemandeurDtoJsonDeserializer implements Deserializer<DemandeurDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public DemandeurDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, DemandeurDTO.class);
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new RuntimeException("Error deserializing DemandeurDto", e);
        }
    }
}
