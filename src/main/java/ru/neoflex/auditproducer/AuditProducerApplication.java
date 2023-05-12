package ru.neoflex.auditproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AuditProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditProducerApplication.class, args);
    }

}
