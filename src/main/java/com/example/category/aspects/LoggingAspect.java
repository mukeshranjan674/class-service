package com.example.category.aspects;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Around("@annotation(com.example.category.annotations.LogMethodParam)")
	public Object logMethodAndTrace(ProceedingJoinPoint pjp) throws Throwable{
		
		String traceId = MDC.get("traceId");
        String methodName = pjp.getSignature().toShortString();
        Object[] args = pjp.getArgs();

        logger.info("[traceId={}] Entering {} with args: {}", traceId, methodName, Arrays.toString(args));

        Object result = pjp.proceed();

        logger.info("[traceId={}] Exiting {} with result: {}", traceId, methodName, result);

        return result;
		
	}

}
