package ilike.ildrio.controller.company; 

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
import ilike.ildrio.model.company.CompanyInfoModel; 
import ilike.ildrio.service.company.CompanyInfoService; 
 

@Controller 
public class CompanyInfoController {  

	@Autowired
	CommonService commonService; 

	@Autowired
	CompanyInfoService companyInfoService; 


	@RequestMapping("companyInfo/companyInfoGrid") 
	public String companyInfoGrid(CompanyInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/company/companyInfo/companyInfoGrid"; 
	} 

	@RequestMapping("companyInfo/companyInfoList") 
	public String companyInfoList(CompanyInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/company/companyInfo/companyInfoList"; 
	} 

	@ResponseBody 
	@RequestMapping("companyInfo/companyInfoListAjax") 
	public HashMap<String, Object> companyInfoListAjax(CompanyInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		int iPageBlock = 10; 
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); 
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); 
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock); 
		int iStartRows = (iCurrPage - 1) * iPageRow; 
		
		reqModel.setStartRow(String.valueOf(iStartRows)); 
		reqModel.setPageRow(String.valueOf(iPageRow)); 
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "")); 
		
		Map<String, Object> rsData = companyInfoService.getListMap_companyInfo(reqModel); 
		List<CompanyInfoModel> rsList = (List<CompanyInfoModel>) rsData.get("rsList"); 
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock)); 
		
		data.put("rsList", rsList); 
		data.put("totRow", totRow); 
		
		return data; 
	} 

	@ResponseBody 
	@RequestMapping("companyInfo/companyInfoSubListAjax") 
	public HashMap<String, Object> companyInfoSubListAjax(CompanyInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		List<CompanyInfoModel> rsList = companyInfoService.subList_companyInfo(reqModel); 
		
		data.put("rsList", rsList); 
		
		return data; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("companyInfo/companyInfoListTrxn") 
	public HashMap<String, Object> companyInfoListTrxn(HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			CompanyInfoModel reqModel = new CompanyInfoModel(); 
			reqModel.setTrxnMode(req.getParameter("trxnMode")); 
 			
			int irow = req.getParameterValues("chkbFlag").length; 
			for (int k = 0; k < irow; k++) { 
 			
				String chkbFlag = req.getParameterValues("chkbFlag")[k]; 
 				
				if (chkbFlag.equals("true")) { 
 					
 					//키컬럼 
					reqModel.setCompanyId(req.getParameterValues("companyId")[k]); 
 					
 					
 					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) { 
 						//수정컬럼 
						companyInfoService.update_companyInfo(reqModel); 
					} 
 					
 					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) { 
						companyInfoService.delete_companyInfo(reqModel); 
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

	@RequestMapping("companyInfo/companyInfoReg") 
	public String companyInfoReg(CompanyInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		CompanyInfoModel rsModel = (CompanyInfoModel) companyInfoService.getMap_companyInfo(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getCompanyId()).equals("")) {	
			rsModel.setCompanyId(String.valueOf(commonService.sequence_common()));	
			rsModel.setTrxnMode("insert");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/company/companyInfo/companyInfoReg"; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("companyInfo/companyInfoTrxn") 
	public HashMap<String, Object> companyInfoTrxn(CompanyInfoModel reqModel,HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			data.put("rsMsg", "FAIL"); 
 			// 입력 
			if (req.getParameter("trxnMode").equals("insert")) { 
 			
					int checkCount = companyInfoService.getCount_companyInfo(reqModel); 
					if (checkCount > 0) { 
						data.put("rsMsg", "DUPLICATE"); 
					} else { 
					companyInfoService.insert_companyInfo(reqModel); 
						data.put("rsMsg", "등록하였습니다."); 
					} 
			} 
 			//  수정
			if (req.getParameter("trxnMode").equals("update")) { 
				companyInfoService.update_companyInfo(reqModel); 
				data.put("rsMsg", "수정하였습니다."); 
			} 
 			
 			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) { 
				companyInfoService.delete_companyInfo(reqModel); 
				data.put("rsMsg", "삭제하였습니다."); 
			} 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("companyInfo/companyInfoView") 
	public String companyInfoView(CompanyInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		CompanyInfoModel rsModel = (CompanyInfoModel) companyInfoService.getMap_companyInfo(reqModel);	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/company/companyInfo/companyInfoView"; 
	} 

}