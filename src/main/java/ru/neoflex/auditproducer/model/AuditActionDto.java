package ru.neoflex.auditproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.neoflex.auditproducer.model.type.AuditService;
import ru.neoflex.auditproducer.model.type.AuditType;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditActionDto {
    private UUID uuid;
    private AuditType type;
    private AuditService service;
    private String message;
}
