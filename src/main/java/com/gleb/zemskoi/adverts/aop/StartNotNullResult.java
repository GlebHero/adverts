package com.gleb.zemskoi.adverts.aop;

import com.gleb.zemskoi.adverts.exception.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StartNotNullResult {
    @Around("@annotation(com.gleb.zemskoi.adverts.aop.NotNullResult)")
    public Object nullChecker(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object response = joinPoint.proceed(args);;
        if (response == null) {
            throw new NotFoundException(((MethodSignature) joinPoint.getSignature()).getReturnType().getSimpleName(), String.valueOf(args[0]));
        }
        return response;
    }
}
