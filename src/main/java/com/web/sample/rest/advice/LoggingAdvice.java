package com.web.sample.rest.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution(* com.web.sample.rest.controllers.*.*(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object logMethodInvocation(ProceedingJoinPoint prcJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = prcJoinPoint.getSignature().getName();
        String className = prcJoinPoint.getTarget().toString();
        Object[] array = prcJoinPoint.getArgs();

        log.info("Invoked class: "+className+" and method: "+methodName+ " with arguments "+mapper.writeValueAsString(array));

        Object response = prcJoinPoint.proceed();

        log.info("Invoked class: "+className+" and method: "+methodName+ " with response "+mapper.writeValueAsString(response));

        return response;
    }
}
