package poe.spring.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import poe.spring.Application;

@Aspect
@Component
public class ChronoAspect {

	private static Logger log = LoggerFactory.getLogger(Application.class);


	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}


	@Around("@annotation(poe.spring.annotation.Chrono)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}

	@Around("@within(poe.spring.annotation.Chrono)")
	public Object logExecutionTimeClass(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed class in " + executionTime + "ms");
		return proceed;
	}
}
