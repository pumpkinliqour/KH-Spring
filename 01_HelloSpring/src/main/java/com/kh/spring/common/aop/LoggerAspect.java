package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component //빈객체등록
@Aspect //AOP객체
public class LoggerAspect {
	private Logger logger=LoggerFactory.getLogger(LoggerAspect.class);
	
	/*@Pointcut("execution(* com.kh.spring..*(..))")
	public void myAround() {
		
	}*/
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void myBefore() {
		
	}
	
	@Around("execution(* com.kh.spring..*(..))")
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		/*배열로도 처리 가능*/
		/*Object[] objs=joinPoint.getArgs();
		for(Object o : objs) {
			logger.debug("매개변수"+o);
		}*/
		/*HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getParameterMap();*/
		
		//내가 join 하는곳의 정보
		Signature signature=joinPoint.getSignature();
		//join 하는곳의 정보의 선언된 타입의 이름
		String type=signature.getDeclaringTypeName();
		//메소드 이름
		String methodName=signature.getName();
		String componentName="";
		
		//타입에서 Controller를 찾았을 때 LOG에 \t 붙임
		if(type.indexOf("Controller")>-1) { 
			componentName="Controller \t : ";
		}
		//타입에서 Service를 찾았을 때 LOG에 \t 붙임
		else if(type.indexOf("Service")>-1) {
			componentName="Service \t : ";
		}
		//타입에서 Dao를 찾았을 때 LOG에 \t 붙임
		else if(type.indexOf("Dao")>-1) {
			componentName="Dao \t : ";
		}
		logger.debug("[Around]"+componentName+type+"."+methodName+"()");
		Object obj=joinPoint.proceed();
		//진행
		return obj;
	}
	
	@Before("myBefore()")
	public void beforeAdvice(JoinPoint joinPoint) {
		Signature signature=joinPoint.getSignature();
		logger.debug("[Before]"+signature.getDeclaringTypeName()+" : "+signature.getName());
	}
}
