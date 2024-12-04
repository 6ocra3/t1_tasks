package org.makar.t1_tasks.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(org.makar.t1_tasks.aspect.annotation.LogTimeExecution)")
    public Object LogTimeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.nanoTime();
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("{} @Around error {}", methodName, e);
        }
        Long end = System.nanoTime();
        double executionTimeInMs = (end - start) / 1_000_000.0;

        logger.info("{} time execution is {} ms", methodName, executionTimeInMs);

        return result;
    }

    @AfterReturning(value = "@annotation(org.makar.t1_tasks.aspect.annotation.LogResult)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("{} result: {}", methodName, result);
    }

    @AfterThrowing(value = "@annotation(org.makar.t1_tasks.aspect.annotation.LogError)", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("{} ERROR: {}", methodName, e.getMessage());
    }
}
