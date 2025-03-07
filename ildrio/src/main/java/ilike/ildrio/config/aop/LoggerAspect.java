package ilike.ildrio.config.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggerAspect {

	@Pointcut("within(ilike.ildrio.controller..*) || within(ilike.ildrio.service..*)")
	private void controllerServicePointCut() {
	}

	@Before("controllerServicePointCut()")
	public void log(JoinPoint joinPoint) {
		log.debug("Execution: {}.{}()", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
	}

}
