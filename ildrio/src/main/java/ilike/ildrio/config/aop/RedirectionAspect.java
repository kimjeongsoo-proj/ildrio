package ilike.ildrio.config.aop;

import ilike.ildrio.common.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Log4j2
public class RedirectionAspect {

	@Pointcut("execution(public * ilike.ildrio.controller..*(..)) && @annotation(ilike.ildrio.common.RequiredSession)")
	private void controllerPointcut() {
	}

	@Around("controllerPointcut()")
	public Object redirect(ProceedingJoinPoint joinPoint) throws Throwable {

		// Ignore if return type is String
		if (((MethodSignature) joinPoint.getSignature()).getReturnType() != String.class) {
			log.warn("RedirectionAspect: Not a page request - {}.{}()",
					joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName());
			return joinPoint.proceed();
		}

		// Ignore if servlet req argument is not present
		Optional<Object> requestOptional = Arrays.stream(joinPoint.getArgs()).filter(HttpServletRequest.class::isInstance).findFirst();
		if (!requestOptional.isPresent()) {
			log.warn("RedirectionAspect: No HttpServletRequest argument - {}.{}()",
					joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName());
			return joinPoint.proceed();
		}

		// Pass if session is alive
		HttpServletRequest request = (HttpServletRequest) requestOptional.get();

		// Redirect to logIn page
		String returnValue;
		try {
			joinPoint.proceed();
		} catch (Exception ignored) {
		}
		returnValue = request.getRequestURI().startsWith("/m") ?
				String.format("redirect:/member/login", RequestUtil.getReqPath(request, 2)) :
				String.format("redirect:/member/login", RequestUtil.getReqPath(request, 1));
		log.debug("RedirectionAspect:     ReqPath={}", request.getRequestURI());
		log.debug("RedirectionAspect: ReturnValue={}", returnValue);

		return returnValue;
	}

}
