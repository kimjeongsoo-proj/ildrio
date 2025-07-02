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

import ilike.ildrio.service.common.CommonService; 
import ilike.ildrio.model.job.JobInfoModel; 
import ilike.ildrio.service.job.JobInfoService; 
 

@Controller 
public class JobInfoController {  

	@Autowired
	CommonService commonService; 

	@Autowired
	JobInfoService jobInfoService; 


	@RequestMapping("jobInfo/jobInfoGrid") 
	public String jobInfoGrid(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobInfo/jobInfoGrid"; 
	} 

	@RequestMapping("jobInfo/jobInfoList") 
	public String jobInfoList(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobInfo/jobInfoList"; 
	} 
	
	@RequestMapping("jobInfo/jobEmergencyList") 
	public String jobEmergencyList(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobInfo/jobEmergencyList"; 
	} 
	
	@RequestMapping("jobInfo/jobSearchList") 
	public String jobSearchList(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/job/jobInfo/jobSearchList"; 
	} 

	@ResponseBody 
	@RequestMapping("jobInfo/jobInfoListAjax") 
	public HashMap<String, Object> jobInfoListAjax(JobInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		int iPageBlock = 10; 
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); 
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); 
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock); 
		int iStartRows = (iCurrPage - 1) * iPageRow; 
		
		reqModel.setStartRow(String.valueOf(iStartRows)); 
		reqModel.setPageRow(String.valueOf(iPageRow)); 
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "")); 
		
		Map<String, Object> rsData = jobInfoService.getListMap_jobInfo(reqModel); 
		List<JobInfoModel> rsList = (List<JobInfoModel>) rsData.get("rsList"); 
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock)); 
		
		data.put("rsList", rsList); 
		data.put("totRow", totRow); 
		
		return data; 
	} 

	@ResponseBody 
	@RequestMapping("jobInfo/jobInfoSubListAjax") 
	public HashMap<String, Object> jobInfoSubListAjax(JobInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		List<JobInfoModel> rsList = jobInfoService.subList_jobInfo(reqModel); 
		
		data.put("rsList", rsList); 
		
		return data; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("jobInfo/jobInfoListTrxn") 
	public HashMap<String, Object> jobInfoListTrxn(HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			JobInfoModel reqModel = new JobInfoModel(); 
			reqModel.setTrxnMode(req.getParameter("trxnMode")); 
 			
			int irow = req.getParameterValues("chkbFlag").length; 
			for (int k = 0; k < irow; k++) { 
 			
				String chkbFlag = req.getParameterValues("chkbFlag")[k]; 
 				
				if (chkbFlag.equals("true")) { 
 					
 					//키컬럼 
					reqModel.setJobNo(req.getParameterValues("jobNo")[k]); 
 					
 					
 					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) { 
 						//수정컬럼 
						jobInfoService.update_jobInfo(reqModel); 
					} 
 					
 					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) { 
						jobInfoService.delete_jobInfo(reqModel); 
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

	@RequestMapping("jobInfo/jobInfoReg") 
	public String jobInfoReg(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobInfoModel rsModel = (JobInfoModel) jobInfoService.getMap_jobInfo(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getJobNo()).equals("")) {	
			rsModel.setJobNo(String.valueOf(commonService.sequence_common()));	
			rsModel.setTrxnMode("insert");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobInfo/jobInfoReg"; 
	} 
	
	@RequestMapping("jobInfo/jobInfoNew") 
	public String jobInfoNew(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobInfoModel rsModel = (JobInfoModel) jobInfoService.getMap_jobInfo(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getJobNo()).equals("")) {
			rsModel.setCompanyId(ssMap.get("ssCompamyNo"));
			rsModel.setCustomerId(ssMap.get("ssCustomerId"));
			rsModel.setJobNo(String.valueOf(commonService.sequence_common()));	
			rsModel.setTrxnMode("insert");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobInfo/jobInfoNew"; 
	}
	
	@RequestMapping("jobInfo/jobInfoEdit") 
	public String jobInfoEdit(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobInfoModel rsModel = (JobInfoModel) jobInfoService.getMap_jobInfo(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getJobNo()).equals("")) {
			rsModel.setCompanyId(ssMap.get("ssCompamyNo"));
			rsModel.setCustomerId(ssMap.get("ssCustomerId"));
			rsModel.setJobNo(String.valueOf(commonService.sequence_common()));	
			rsModel.setTrxnMode("update");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobInfo/jobInfoEdit"; 
	}

	@Transactional 
	@ResponseBody 
	@RequestMapping("jobInfo/jobInfoTrxn") 
	public HashMap<String, Object> jobInfoTrxn(JobInfoModel reqModel,HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			data.put("rsMsg", "FAIL"); 
 			// 입력 
			if (req.getParameter("trxnMode").equals("insert")) { 
 			
					int checkCount = jobInfoService.getCount_jobInfo(reqModel); 
					if (checkCount > 0) { 
						data.put("rsMsg", "DUPLICATE"); 
					} else { 
					jobInfoService.insert_jobInfo(reqModel); 
						data.put("rsMsg", "등록하였습니다."); 
					} 
			} 
 			//  수정
			if (req.getParameter("trxnMode").equals("update")) { 
				jobInfoService.update_jobInfo(reqModel); 
				data.put("rsMsg", "수정하였습니다."); 
			} 
 			
 			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) { 
				jobInfoService.delete_jobInfo(reqModel); 
				data.put("rsMsg", "삭제하였습니다."); 
			} 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("jobInfo/jobInfoView") 
	public String jobInfoView(JobInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		JobInfoModel rsModel = (JobInfoModel) jobInfoService.getMap_jobInfo(reqModel);	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/job/jobInfo/jobInfoView"; 
	} 

}