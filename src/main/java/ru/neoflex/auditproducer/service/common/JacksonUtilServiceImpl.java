package ru.neoflex.auditproducer.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.neoflex.auditproducer.model.AuditActionDto;

@Service
@RequiredArgsConstructor
public class JacksonUtilServiceImpl implements JacksonUtilService {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String AuditActionToJson(AuditActionDto message) {
        return objectMapper.writeValueAsString(message);
    }
}
