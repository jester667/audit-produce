package ru.neoflex.auditproducer.kafka;

import ru.neoflex.auditproducer.model.AuditActionDto;

public interface AuditProducer {
    void produceMessage(AuditActionDto message);
}
