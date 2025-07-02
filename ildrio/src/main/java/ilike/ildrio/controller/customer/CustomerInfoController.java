package ilike.ildrio.controller.customer; 

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

import ilike.ildrio.model.customer.CustomerInfoModel; 
import ilike.ildrio.service.customer.CustomerInfoService; 
 

@Controller 
public class CustomerInfoController {  

	@Autowired
	CustomerInfoService customerInfoService; 


	@RequestMapping("customerInfo/customerInfoGrid") 
	public String customerInfoGrid(CustomerInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/customer/customerInfo/customerInfoGrid"; 
	} 

	@RequestMapping("customerInfo/customerInfoList") 
	public String customerInfoList(CustomerInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		return "back/customer/customerInfo/customerInfoList"; 
	} 

	@ResponseBody 
	@RequestMapping("customerInfo/customerInfoListAjax") 
	public HashMap<String, Object> customerInfoListAjax(CustomerInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		int iPageBlock = 10; 
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); 
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); 
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock); 
		int iStartRows = (iCurrPage - 1) * iPageRow; 
		
		reqModel.setStartRow(String.valueOf(iStartRows)); 
		reqModel.setPageRow(String.valueOf(iPageRow)); 
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "")); 
		
		Map<String, Object> rsData = customerInfoService.getListMap_customerInfo(reqModel); 
		List<CustomerInfoModel> rsList = (List<CustomerInfoModel>) rsData.get("rsList"); 
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock)); 
		
		data.put("rsList", rsList); 
		data.put("totRow", totRow); 
		
		return data; 
	} 

	@ResponseBody 
	@RequestMapping("customerInfo/customerInfoSubListAjax") 
	public HashMap<String, Object> customerInfoSubListAjax(CustomerInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
		HashMap<String, Object> data = new HashMap<String, Object>(); 
		
		List<CustomerInfoModel> rsList = customerInfoService.subList_customerInfo(reqModel); 
		
		data.put("rsList", rsList); 
		
		return data; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("customerInfo/customerInfoListTrxn") 
	public HashMap<String, Object> customerInfoListTrxn(HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			CustomerInfoModel reqModel = new CustomerInfoModel(); 
			reqModel.setTrxnMode(req.getParameter("trxnMode")); 
 			
			int irow = req.getParameterValues("chkbFlag").length; 
			for (int k = 0; k < irow; k++) { 
 			
				String chkbFlag = req.getParameterValues("chkbFlag")[k]; 
 				
				if (chkbFlag.equals("true")) { 
 					
 					//키컬럼 
					reqModel.setCustomerId(req.getParameterValues("CustomerId")[k]); 
 					
 					
 					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) { 
 						//수정컬럼 
						customerInfoService.update_customerInfo(reqModel); 
					} 
 					
 					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) { 
						customerInfoService.delete_customerInfo(reqModel); 
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

	@RequestMapping("customerInfo/customerInfoReg") 
	public String customerInfoReg(CustomerInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		CustomerInfoModel rsModel = (CustomerInfoModel) customerInfoService.getMap_customerInfo(reqModel);	
		//pk컬럼 
		if (StringUtil.NVL(rsModel.getCustomerId()).equals("")) {	
			rsModel.setCustomerId(reqModel.getCustomerId());	
			rsModel.setTrxnMode("insert");	
		}	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/customer/customerInfo/customerInfoReg"; 
	} 

	@Transactional 
	@ResponseBody 
	@RequestMapping("customerInfo/customerInfoTrxn") 
	public HashMap<String, Object> customerInfoTrxn(CustomerInfoModel reqModel,HttpServletRequest req) throws Exception { 
 		
		// ------------------------------  
		HttpSession httpSession = req.getSession(); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		// -------------------------------          
		HashMap<String, Object> data = new HashMap<>(); 
		try { 
 		
			data.put("rsMsg", "FAIL"); 
 			// 입력 
			if (req.getParameter("trxnMode").equals("insert")) { 
 			
					int checkCount = customerInfoService.getCount_customerInfo(reqModel); 
					if (checkCount > 0) { 
						data.put("rsMsg", "DUPLICATE"); 
					} else { 
					customerInfoService.insert_customerInfo(reqModel); 
						data.put("rsMsg", "등록하였습니다."); 
					} 
			} 
 			//  수정
			if (req.getParameter("trxnMode").equals("update")) { 
				customerInfoService.update_customerInfo(reqModel); 
				data.put("rsMsg", "수정하였습니다."); 
			} 
 			
 			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) { 
				customerInfoService.delete_customerInfo(reqModel); 
				data.put("rsMsg", "삭제하였습니다."); 
			} 
 			
		} catch (Exception e) { 
			System.out.println(e); 
			data.put("rsMsg", "FAIL"); 
		} 
		return data; 
	} 

	@RequestMapping("customerInfo/customerInfoView") 
	public String customerInfoView(CustomerInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception { 
		
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Pragma", "no-cache"); 
		
		// ------------------------------       
		HttpSession httpSession = req.getSession(); 
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), "")); 
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession); 
		model.addAttribute("ssMap", ssMap); 
		model.addAttribute("reqModel", reqModel); 
		// -------------------------------  
		
		CustomerInfoModel rsModel = (CustomerInfoModel) customerInfoService.getMap_customerInfo(reqModel);	
		
		model.addAttribute("rsModel", rsModel);	
		
		return "back/customer/customerInfo/customerInfoView"; 
	} 

}