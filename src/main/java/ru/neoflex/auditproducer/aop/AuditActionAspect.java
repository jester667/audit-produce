package ru.neoflex.auditproducer.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.neoflex.auditproducer.kafka.AuditProducer;
import ru.neoflex.auditproducer.model.AuditActionDto;
import ru.neoflex.auditproducer.model.type.AuditService;
import ru.neoflex.auditproducer.model.type.AuditType;

import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditActionAspect {
    private final AuditProducer auditProducer;
    private static final String START_MESSAGE_PATTERN = "%s.%s called with parameters: %s";
    private static final String SUCCESS_MESSAGE_PATTERN = "%s.%s success";
    private static final String ERROR_MESSAGE_PATTERN = "Exception occurred in %s.%s : %s";

    @Before("@annotation(auditAction)")
    public void startLog(JoinPoint joinPoint, AuditAction auditAction) {
        auditProducer.produceMessage(
                AuditActionDto.builder()
                        .uuid(UUID.randomUUID())
                        .type(AuditType.START)
                        .service(AuditService.valueOf(auditAction.auditService()))
                        .message(String.format(START_MESSAGE_PATTERN,
                                joinPoint.getTarget().getClass().getSimpleName(),
                                joinPoint.getSignature().getName(),
                                Arrays.toString(joinPoint.getArgs())))
                        .build()
        );
    }

    @AfterReturning("@annotation(auditAction)")
    public void successLog(JoinPoint joinPoint, AuditAction auditAction) {
        auditProducer.produceMessage(
                AuditActionDto.builder()
                        .uuid(UUID.randomUUID())
                        .type(AuditType.SUCCESS)
                        .service(AuditService.valueOf(auditAction.auditService()))
                        .message(String.format(SUCCESS_MESSAGE_PATTERN,
                                joinPoint.getTarget().getClass().getSimpleName(),
                                joinPoint.getSignature().getName()))
                        .build()
        );
    }

    @AfterThrowing(value = "@annotation(auditAction)", throwing = "ex")
    public void exceptionLog(JoinPoint joinPoint, AuditAction auditAction, Exception ex) {
        auditProducer.produceMessage(
                AuditActionDto.builder()
                        .uuid(UUID.randomUUID())
                        .type(AuditType.FAILURE)
                        .service(AuditService.valueOf(auditAction.auditService()))
                        .message(String.format(ERROR_MESSAGE_PATTERN,
                                joinPoint.getTarget().getClass().getSimpleName(),
                                joinPoint.getSignature().getName(),
                               ex.getMessage()))
                        .build()
        );
    }
}
