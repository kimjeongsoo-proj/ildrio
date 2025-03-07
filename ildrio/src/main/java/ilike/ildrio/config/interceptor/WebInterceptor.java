package ilike.ildrio.config.interceptor;

import ilike.ildrio.common.ErrorModel;
import ilike.ildrio.common.SessionUtil;
import ilike.ildrio.common.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@Log4j2
public class WebInterceptor implements HandlerInterceptor {

	private final SessionUtil sessionUtil;

	public WebInterceptor(SessionUtil sessionUtil) {
		this.sessionUtil = sessionUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//String requestURL = request.getRequestURL().toString();
		//String requestURI = request.getRequestURI();

		return websiteShopId(request, response);
	}


	public boolean websiteShopId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestURI = request.getRequestURI().toString();

		System.out.println("requestURI :: " + requestURI);

		return true;
	}

}
