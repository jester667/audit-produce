package ru.neoflex.auditproducer.service.common;

import ru.neoflex.auditproducer.model.AuditActionDto;

public interface JacksonUtilService {
    String AuditActionToJson(AuditActionDto message);
}
