package com.gleb.zemskoi.adverts.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class StartLogJournal {
    @Around("@annotation(com.gleb.zemskoi.adverts.aop.logging.LogJournal)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        Object response;
        try {
            log.info("before method " + methodName + " with " + Arrays.toString(args));
            response = joinPoint.proceed(args);
            log.info("after method " + methodName + " result " + response);
        } catch (Throwable throwable) {
            log.error("error method " + methodName + " with exception " + throwable.getMessage());
            throw throwable;
        }
        return response;
    }
}
