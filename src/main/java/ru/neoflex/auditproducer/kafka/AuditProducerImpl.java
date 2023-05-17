package ru.neoflex.auditproducer.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.neoflex.auditproducer.model.AuditActionDto;
import ru.neoflex.auditproducer.service.common.JacksonUtilService;

@Component
@RequiredArgsConstructor
public class AuditProducerImpl implements AuditProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JacksonUtilService jacksonUtilService;
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Override
    public void produceMessage(AuditActionDto message) {
        var messageJson = jacksonUtilService.AuditActionToJson(message);
        kafkaTemplate.send(topic, messageJson);
    }
}
