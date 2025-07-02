package com.fantasmaDux.restApplication.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic newToic() {
        return new NewTopic(
                "kafka-test",
                1,
                (short) 1
        );
    }
}
