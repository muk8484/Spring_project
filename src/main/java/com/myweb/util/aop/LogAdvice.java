package com.myweb.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

// AOP 프로그램 중 aspect 프로그램임을 알려준다.
@Aspect
@Component
@Log4j
public class LogAdvice {
	
	// around aop 처리
	@Around("execution(* com.myweb.*.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		String signature = pjp.getSignature().toShortString();
		Object result = null;
		
		// -----------여기에 작성한 코드들은 실행 이전에 처리된다. - 넘어오는 데이터 출력
		// 시작 시간 저장
		long start = System.currentTimeMillis();
 		
		log.info("<<=============== Service Log ===============>");
		
		// 실행하려는 서비스 프로그램 타입
		log.info(" - 실행 서비스 : " + signature + pjp.getTarget());
		log.info(" - 넘어가는 데이터 : " + Arrays.toString(pjp.getArgs()));
		
		// AOP 가 여러개인 경우 다음 APP로 이동해야한다.
		try {
			// 다음 진행으로 이동
			result =  pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// -----------여기에 작성한 코드들은 실행 이후에 처리된다. - 실행결과 데이터 출력
		long end = System.currentTimeMillis();
		
		// 처리할때 걸린시간
		log.info("Time : " + ((end-start) / 1000.0 + "초"));
		
		log.info("<<==================================================>");
		return result;
	}
	
	
}
