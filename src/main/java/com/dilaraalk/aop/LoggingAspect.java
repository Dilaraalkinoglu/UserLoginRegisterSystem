package com.dilaraalk.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(public * com.dilaraalk.service..*(..))")
	private void serviceMethods() {
		
	}
	
	@Before(value = "serviceMethods()")
	public void logBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String methodName = joinPoint.getSignature().getName();
		logger.debug(">> {}() - {}", methodName,Arrays.toString(args));
		
	}
	
	
	@AfterReturning(value = "serviceMethods()",returning = "result")
	public void logAfter(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		logger.debug(">> {}() - {}", methodName,result);
	}

}
