package ilike.ildrio.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author KimJungSu
 * @packageName :ilike.ildrio.common
 * @fileName : RequestUtil.java
 * @date : 2021. 9. 29.
 * @description :
 * ==========================================
 * DATE       		AUTHOR 			CONTENT
 * -------------------------------------------
 * 2021.09.29       KimJungSu      RequestConverMap
 * 2021.09.29       js.kim     RequestConverObject
 */
public class RequestUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * FORM REQUEST -> MAP
	 *
	 * @param request
	 * @return
	 * @methodName : getParameterMap
	 * @author : js.kim
	 * @date : 2021. 9. 29.
	 */
	public static Map getParameterMap(HttpServletRequest request) {

		Map parameterMap = new HashMap();
		Enumeration enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			if (parameters.length > 1) {
				parameterMap.put(paramName, parameters);
			} else {
				parameterMap.put(paramName, parameters[0]);
			}
		}

		return parameterMap;
	}

	/**
	 * FORM REQUEST -> OBJECT
	 *
	 * @param request
	 * @return
	 * @methodName : getParameterObject
	 * @author : js.kim
	 * @date : 2021. 9. 29.
	 */
	public static Object getParameterObject(HttpServletRequest request, Object obj) {

		Map parameterMap = new HashMap();
		Enumeration enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			if (parameters.length > 1) {
				parameterMap.put(paramName, parameters);
			} else {
				parameterMap.put(paramName, parameters[0]);
			}
		}
		return mapper.convertValue(parameterMap, obj.getClass());
	}

	/**
	 * Check if request came from mobile or PC
	 *
	 * @param request ServletRequest
	 * @return boolean Request came from mobile
	 * @author Beom
	 * @date 2021. 11. 22.
	 */
	public static boolean isMobileReq(HttpServletRequest request) {
		return "mobile".equals(request.getParameter("browser"));
	}

	/**
	 * Check if the request is XmlHttp
	 *
	 * @param request ServletRequest
	 * @return boolean request is XmlHttp
	 * @author Beom
	 * @Date 2021. 11. 29.
	 */
	public static boolean isXmlHttpReq(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	/**
	 * Get a specific depth of request path
	 *
	 * @param request ServletRequest
	 * @param depth   Depth
	 * @return path
	 * @author Beom
	 * @Date 2021. 12. 06.
	 */
	public static String getReqPath(HttpServletRequest request, int depth) {
		String[] paths = request.getRequestURI().split("/");
		if (paths.length < depth + 1) {
			return "";
		}
		return paths[depth];
	}

	protected String getClientIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null) {
			ipAddress = request.getHeader("Proxy-Client-IP");
			if (ipAddress == null) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
			}
		}

		return ipAddress;
	}
}
