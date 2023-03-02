package com.minty.salesinventorymgt.config;

import com.minty.lib.utils.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaTopicConfig {

    @Value("${topic-name:ORDER-CREATION}")
    private String name;

    @Bean
    public NewTopic orderCreationTopic() {

        return TopicBuilder.name(TopicConstant.ORDER_TOPIC).build();
    }


}