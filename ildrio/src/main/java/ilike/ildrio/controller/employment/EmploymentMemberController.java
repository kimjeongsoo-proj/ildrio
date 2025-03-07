package ilike.ildrio.controller.employment; 

import egovframework.rte.psl.dataaccess.mapper.Mapper; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody; 
 
import java.util.List;  
import java.util.Map; 
import java.util.HashMap;  
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
 
import ilike.ildrio.common.SessionParam; 
import ilike.ildrio.common.StringUtil; 

import ilike.ildrio.model.employment.EmploymentMemberModel; 
import ilike.ildrio.service.employment.EmploymentMemberService; 
 

@Controller 
public class EmploymentMemberController {  

	@Autowired
	EmploymentMemberService employmentMemberService; 


	@RequestMapping("employmentMember/employmentMemberGrid") 
	public String employmentMemberGrid(EmploymentMemberModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/employment/employmentMember/employmentMemberGrid"; 
	} 

	@RequestMapping("employmentMember/employmentMemberList") 
	public String employmentMemberList(EmploymentMemberModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/employment/employmentMember/employmentMemberList"; 
	} 

	@ResponseBody 
	@RequestMapping("employmentMember/employmentMemberListAjax") 
	public HashMap<String, Object> employmentMemberListAjax(EmploymentMemberModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		int iPageBlock = 10; 
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); 
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); 
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock); 
		int iStartRows = (iCurrPage - 1) * iPageRow; 
		
		reqModel.setStartRow(String.valueOf(iStartRows)); 
		reqModel.setPageRow(String.valueOf(iPageRow)); 
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "")); 
		
		Map<String, Object> rsData = employmentMemberService.getListMap_employmentMember(reqModel); 
		List<EmploymentMemberModel> rsList = (List<EmploymentMemberModel>) rsData.get("rsList"); 
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock)); 
		
		data.put("rsList", rsList); 
		data.put("totRow", totRow); 
		
		return data; 
	} 

	@ResponseBody 
	@RequestMapping("employmentMember/employmentMemberSubListAjax") 
	public HashMap<String, Object> employmentMemberSubListAjax(EmploymentMemberModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		List<EmploymentMemberModel> rsList = employmentMemberService.subList_employmentMember(reqModel); 
		
		data.put("rsList", rsList); 
		
		return data; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("employmentMember/employmentMemberListTrxn") 
	public HashMap<String, Object> employmentMemberListTrxn(HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			EmploymentMemberModel reqModel = new EmploymentMemberModel(); 
			reqModel.setTrxnMode(req.getParameter("trxnMode")); 
 			
			int irow = req.getParameterValues("chkbFlag").length; 
			for (int k = 0; k < irow; k++) { 
 			
				String chkbFlag = req.getParameterValues("chkbFlag")[k]; 
 				
				if (chkbFlag.equals("true")) { 
 					
 					//키컬럼 
					reqModel.setJobNo(req.getParameterValues("jobNo")[k]); 
					reqModel.setTechCode(req.getParameterValues("techCode")[k]); 
					reqModel.setCareerCode(req.getParameterValues("careerCode")[k]); 
					reqModel.setMemberNo(req.getParameterValues("memberNo")[k]); 
 					
 					
 					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) { 
 						//수정컬럼 
						employmentMemberService.update_employmentMember(reqModel); 
					} 
 					
 					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) { 
						employmentMemberService.delete_employmentMember(reqModel); 
					} 
				} 
			} 
			data.put("rsMsg", "SUCCESS"); 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("employmentMember/employmentMemberReg") 
	public String employmentMemberReg(EmploymentMemberModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		EmploymentMemberModel rsModel = (EmploymentMemberModel) employmentMemberService.getMap_employmentMember(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getMemberNo()).equals("")) {	
			rsModel.setJobNo(reqModel.getJobNo());	
			rsModel.setTechCode(reqModel.getTechCode());	
			rsModel.setCareerCode(reqModel.getCareerCode());	
			rsModel.setMemberNo(reqModel.getMemberNo());	
			rsModel.setTrxnMode("insert");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/employment/employmentMember/employmentMemberReg"; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("employmentMember/employmentMemberTrxn") 
	public HashMap<String, Object> employmentMemberTrxn(EmploymentMemberModel reqModel,HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			data.put("rsMsg", "FAIL"); 
 			// 입력 
			if (req.getParameter("trxnMode").equals("insert")) { 
 			
					int checkCount = employmentMemberService.getCount_employmentMember(reqModel); 
					if (checkCount > 0) { 
						data.put("rsMsg", "DUPLICATE"); 
					} else { 
					employmentMemberService.insert_employmentMember(reqModel); 
						data.put("rsMsg", "등록하였습니다."); 
					} 
			} 
 			//  수정
			if (req.getParameter("trxnMode").equals("update")) { 
				employmentMemberService.update_employmentMember(reqModel); 
				data.put("rsMsg", "수정하였습니다."); 
			} 
 			
 			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) { 
				employmentMemberService.delete_employmentMember(reqModel); 
				data.put("rsMsg", "삭제하였습니다."); 
			} 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("employmentMember/employmentMemberView") 
	public String employmentMemberView(EmploymentMemberModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		EmploymentMemberModel rsModel = (EmploymentMemberModel) employmentMemberService.getMap_employmentMember(reqModel);	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/employment/employmentMember/employmentMemberView"; 
	} 

}