package ilike.ildrio.controller.job; 

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

import ilike.ildrio.model.job.JobApplyModel; 
import ilike.ildrio.service.job.JobApplyService; 
 

@Controller 
public class JobApplyController {  

	@Autowired
	JobApplyService jobApplyService; 


	@RequestMapping("jobApply/jobApplyGrid") 
	public String jobApplyGrid(JobApplyModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobApply/jobApplyGrid"; 
	} 

	@RequestMapping("jobApply/jobApplyList") 
	public String jobApplyList(JobApplyModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobApply/jobApplyList"; 
	} 

	@ResponseBody 
	@RequestMapping("jobApply/jobApplyListAjax") 
	public HashMap<String, Object> jobApplyListAjax(JobApplyModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		int iPageBlock = 10; 
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); 
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); 
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock); 
		int iStartRows = (iCurrPage - 1) * iPageRow; 
		
		reqModel.setStartRow(String.valueOf(iStartRows)); 
		reqModel.setPageRow(String.valueOf(iPageRow)); 
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "")); 
		
		Map<String, Object> rsData = jobApplyService.getListMap_jobApply(reqModel); 
		List<JobApplyModel> rsList = (List<JobApplyModel>) rsData.get("rsList"); 
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock)); 
		
		data.put("rsList", rsList); 
		data.put("totRow", totRow); 
		
		return data; 
	} 

	@ResponseBody 
	@RequestMapping("jobApply/jobApplySubListAjax") 
	public HashMap<String, Object> jobApplySubListAjax(JobApplyModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		List<JobApplyModel> rsList = jobApplyService.subList_jobApply(reqModel); 
		
		data.put("rsList", rsList); 
		
		return data; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("jobApply/jobApplyListTrxn") 
	public HashMap<String, Object> jobApplyListTrxn(HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			JobApplyModel reqModel = new JobApplyModel(); 
			reqModel.setTrxnMode(req.getParameter("trxnMode")); 
 			
			int irow = req.getParameterValues("chkbFlag").length; 
			for (int k = 0; k < irow; k++) { 
 			
				String chkbFlag = req.getParameterValues("chkbFlag")[k]; 
 				
				if (chkbFlag.equals("true")) { 
 					
 					
 					
 					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) { 
 						//수정컬럼 
						jobApplyService.update_jobApply(reqModel); 
					} 
 					
 					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) { 
						jobApplyService.delete_jobApply(reqModel); 
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

	@RequestMapping("jobApply/jobApplyReg") 
	public String jobApplyReg(JobApplyModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobApplyModel rsModel = (JobApplyModel) jobApplyService.getMap_jobApply(reqModel);	
		
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobApply/jobApplyReg"; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("jobApply/jobApplyTrxn") 
	public HashMap<String, Object> jobApplyTrxn(JobApplyModel reqModel,HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			data.put("rsMsg", "FAIL"); 
 			// 입력 
			if (req.getParameter("trxnMode").equals("insert")) { 
 			
					int checkCount = jobApplyService.getCount_jobApply(reqModel); 
					if (checkCount > 0) { 
						data.put("rsMsg", "DUPLICATE"); 
					} else { 
					jobApplyService.insert_jobApply(reqModel); 
						data.put("rsMsg", "등록하였습니다."); 
					} 
			} 
 			//  수정
			if (req.getParameter("trxnMode").equals("update")) { 
				jobApplyService.update_jobApply(reqModel); 
				data.put("rsMsg", "수정하였습니다."); 
			} 
 			
 			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) { 
				jobApplyService.delete_jobApply(reqModel); 
				data.put("rsMsg", "삭제하였습니다."); 
			} 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("jobApply/jobApplyView") 
	public String jobApplyView(JobApplyModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobApplyModel rsModel = (JobApplyModel) jobApplyService.getMap_jobApply(reqModel);	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobApply/jobApplyView"; 
	} 

}