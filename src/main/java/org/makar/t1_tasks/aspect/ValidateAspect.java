package org.makar.t1_tasks.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidateAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidateAspect.class);

    @Before("@annotation(org.makar.t1_tasks.aspect.annotation.ValidateId)")
    public void validateTaskId(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Long id = (Long) joinPoint.getArgs()[0];
        if(id < 0){
            IllegalArgumentException e = new IllegalArgumentException("ID must be positive");
            logger.error("{} ValidateAspect ID ERROR: {}", methodName, e);
            throw e;
        }
    }

}
