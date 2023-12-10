package com.example.msjobseeker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ComponentScan(basePackages = "com.example.msjobseeker")
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name2}")
    private String topicName;

    //search about what partition mean in topic

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .build();
    }

}
