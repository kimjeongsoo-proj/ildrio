package ilike.ildrio.controller.company; 

import egovframework.rte.psl.dataaccess.mapper.Mapper; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;  
import java.util.Map; 
import java.util.HashMap;  
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
 
import ilike.ildrio.common.SessionParam; 
import ilike.ildrio.common.StringUtil;
import ilike.ildrio.config.security.JwtTokenProvider;
import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.model.company.CompanyInfoModel; 
import ilike.ildrio.service.company.CompanyInfoService; 
 

@RestController
@RequestMapping("/api")
public class ApiCompanyInfoController {  

	@Autowired
	CommonService commonService; 

	@Autowired
	CompanyInfoService companyInfoService; 

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	
	@ResponseBody 
	@RequestMapping("/apiCompanyInfoList") 
	public HashMap<String, Object> apiCompanyInfoList(CompanyInfoModel reqModel, HttpServletRequest req) throws Exception { 
		
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
	@RequestMapping("/apiCompanyInfo/{companyId}")
	public HashMap<String, Object> apiCompanyInfo(@PathVariable String companyId, HttpServletRequest req) {
	  
	  HashMap<String, Object> data = new HashMap<>();
	  
	  CompanyInfoModel reqModel= new CompanyInfoModel();
	  reqModel.setCompanyId(companyId);
	  
	  CompanyInfoModel Company = companyInfoService.getMap_companyInfo(reqModel);
	  data.put("rsData", Company); // rsData 키로 감싸기
	  
	  return data;
	}

	@Transactional 
	@ResponseBody 
	@RequestMapping("/apiCompanyInfoTrxn") 
	public HashMap<String, Object> apiCompanyInfoTrxn(CompanyInfoModel reqModel,HttpServletRequest req) throws Exception { 
 		
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

	
}