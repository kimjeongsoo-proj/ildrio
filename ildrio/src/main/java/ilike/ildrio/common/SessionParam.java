package ilike.ildrio.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ilike.ildrio.common.StringUtil;
import ilike.ildrio.common.DBCPTransaction;

public class SessionParam {
	/**
	 * 
	 * @param httpSession
	 * @return
	 */
	public static Map<String, String> getSessionMap(HttpSession httpSession) {

		Map<String, String> ssMap = new HashMap<String, String>();

		ssMap.put("ssMenuGroup", StringUtil.NVLS((String) httpSession.getAttribute("ssMenuGroup"), "company"));
		ssMap.put("ssMenuCode", StringUtil.NVLS((String) httpSession.getAttribute("ssMenuCode"), ""));

		ssMap.put("ssCompanyId", StringUtil.NVLS((String) httpSession.getAttribute("ssCompanyId"), ""));
		ssMap.put("ssCompanyName", StringUtil.NVLS((String) httpSession.getAttribute("ssCompanyName"), ""));

		ssMap.put("ssMemberId", StringUtil.NVLS((String) httpSession.getAttribute("ssMemberId"), ""));
		ssMap.put("ssCustomerName", StringUtil.NVLS((String) httpSession.getAttribute("ssCustomerName"), ""));
		
		ssMap.put("ssMemberNo", StringUtil.NVLS((String) httpSession.getAttribute("ssMemberNo"), ""));
		ssMap.put("ssMemberId", StringUtil.NVLS((String) httpSession.getAttribute("ssMemberId"), ""));
		ssMap.put("ssMemberName", StringUtil.NVLS((String) httpSession.getAttribute("ssMemberName"), ""));
		
	
	
		return ssMap;
	}

	public static String getNewId(String prefix) throws Exception {

		String sql = " select nextval(sys_sequence) seq_no ";
		
		Map<String, String> rsMap = DBCPTransaction.dbcpSelectMap(sql);
		
		return prefix + rsMap.get("seq_no");
	}
}