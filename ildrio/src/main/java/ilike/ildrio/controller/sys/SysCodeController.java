package ilike.ildrio.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ilike.ildrio.common.RequestUtil;
import ilike.ildrio.common.RequiredSession;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.service.common.CommonService;

import ilike.ildrio.model.sys.SysCodeModel;
import ilike.ildrio.service.sys.SysCodeService;

import ilike.ildrio.controller.sys.SysCodeFunction;
import ilike.ildrio.controller.sys.SysCodeDataGen;

@Controller
public class SysCodeController {

	@Autowired
	CommonService commonService;

	@Autowired
	SysCodeService sysCodeService;

	// 공통코드 리스트 페이지 
	@RequestMapping("sysCode/sysCodeList")
	public String sysCodeList(SysCodeModel sysCodeModel, HttpServletRequest req, Model model, HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		// ------------------------------- 

		return "back/sys/sysCode/sysCodeList";
	}

	// 공통코드 그리드 리스트 페이지 
	@RequestMapping("sysCode/sysCodeGist")
	public String sysCodeGist(SysCodeModel sysCodeModel, HttpServletRequest req, Model model, HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		// -------------------------------                                    

		model.addAttribute("divLeftWidth", "500");

		return "back/sys/sysCode/sysCodeGist";
	}

	// 공통코드 리스트 AJAX
	@ResponseBody
	@RequestMapping("sysCode/sysCodeListAjax")
	public HashMap<String, Object> sysCodeListAjax(SysCodeModel sysCodeModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		int iPageBlock = 10;
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20"));
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
		if ((iCurrPage % iPageBlock) > 0) {
			iCurrBlock = iCurrBlock + 1;
		}
		int iStartRows = (iCurrBlock - 1) * iPageRow * iPageBlock;
		int iPageRowCount = iPageRow * iPageBlock + 1;

		sysCodeModel.setStartRow(String.valueOf(iStartRows));
		sysCodeModel.setPageRow(String.valueOf(iPageRowCount));
		sysCodeModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), ""));

		List<SysCodeModel> rsList = sysCodeService.getList_sysCode(sysCodeModel);

		data.put("listPageNavigation", StringUtil.getListPageNavigation(rsList.size(), iPageRow, iCurrPage, iPageBlock));
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(rsList.size(), iPageRow, iCurrPage, iPageBlock));

		int iDisplayStart = (iCurrPage - 1 - (iCurrBlock - 1) * iPageBlock) * iPageRow;
		int iDisplayEnd = (iCurrPage - (iCurrBlock - 1) * iPageBlock) * iPageRow;
		if (iDisplayEnd > rsList.size()) {
			iDisplayEnd = rsList.size();
		}
		data.put("rsList", rsList);
		data.put("startRow", iDisplayStart);
		data.put("endRow", iDisplayEnd);

		return data;
	}

	// 공통코드 등록&수정 
	@RequestMapping("sysCode/sysCodeEdit")
	public String sysCodeEdit(SysCodeModel sysCodeModel, HttpServletRequest req, Model model) {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		// -------------------------------                                    

		SysCodeModel rsModel = (SysCodeModel) sysCodeService.getMap_sysCode(sysCodeModel);
		if (StringUtil.NVL(rsModel.getCodeValue()).equals("")) {
			rsModel.setTrxnMode("insert");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/sys/sysCode/sysCodeEdit";
	}

	// 공통코드 신규작성 
	@RequestMapping("sysCode/sysCodeNew")
	public String sysCodeNew(HttpServletRequest req, Model model) {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		// -------------------------------                                    

		SysCodeModel rsModel = new SysCodeModel();
		rsModel.setTrxnMode("insert");

		model.addAttribute("rsModel", rsModel);

		return "back/sys/sysCode/sysCodeNew";
	}

	// 공통코드 조회
	@RequestMapping("sysCode/sysCodeView")
	public String sysCodeView(SysCodeModel sysCodeModel, HttpServletRequest req, Model model) {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		// -------------------------------                                    

		model.addAttribute("rsMap", sysCodeService.getMap_sysCode(sysCodeModel));

		return "back/sys/sysCode/sysCodeView";
	}

	// 공통코드 조회 AJAX
	@ResponseBody
	@RequestMapping("sysCode/sysCodeViewAjax")
	public HashMap<String, Object> sysCodeViewAjax(SysCodeModel sysCodeModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("rsMap", sysCodeService.getMap_sysCode(sysCodeModel));

		return data;
	}

	// 공통코드 등록&수정 트렌젝션
	@Transactional
	@ResponseBody
	@RequestMapping("sysCode/sysCodeTrxn")
	public HashMap<String, Object> sysCodeTrxn(SysCodeModel sysCodeModel, HttpServletRequest req) throws Exception {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------                                    
		HashMap<String, Object> data = new HashMap<>();
		try {
			if (req.getParameter("trxnMode").equals("insert")) {
				int checkCount = sysCodeService.getChkCnt_sysCode(sysCodeModel);
				if (checkCount > 0) {
					data.put("rsMsg", "DUPLICATE");
				} else {
					sysCodeService.insert_sysCode(sysCodeModel);
					data.put("rsMsg", "등록하였습니다.");
				}
			}
			if (req.getParameter("trxnMode").equals("update")) {
				sysCodeService.update_sysCode(sysCodeModel);
				data.put("rsMsg", "수정하였습니다.");
			}
			if (req.getParameter("trxnMode").equals("delete")) {
				sysCodeService.delete_sysCode(sysCodeModel);
				data.put("rsMsg", "삭제하였습니다.");
			}
			if (req.getParameter("trxnMode").equals("view")) {

				data.put("rsData", sysCodeService.getMap_sysCode(sysCodeModel));
			}
		} catch (Exception e) {
			System.out.println(e);
			data.put("rsMsg", "FAIL");
		}
		return data;
	}

	// 공통코드 리스트 수정/삭제 트렌젝션
	@Transactional
	@ResponseBody
	@RequestMapping("sysCode/sysCodeListTrxn")
	public HashMap<String, Object> sysCodeListTrxn(HttpServletRequest req) throws Exception {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------                                    
		HashMap<String, Object> data = new HashMap<>();
		try {

			SysCodeModel sysCodeModel = new SysCodeModel();
			sysCodeModel.setTrxnMode(req.getParameter("trxnMode"));

			int irow = req.getParameterValues("chkbFlag").length;
			for (int k = 0; k < irow; k++) {

				String chkbFlag = req.getParameterValues("chkbFlag")[k];

				if (chkbFlag.equals("true")) {

					sysCodeModel.setCodeId(req.getParameterValues("codeId")[k]);
					sysCodeModel.setCodeValue(req.getParameterValues("codeValue")[k]);
					sysCodeModel.setCodeText(req.getParameterValues("codeText")[k]);
					sysCodeModel.setSortOrder(req.getParameterValues("sortOrder")[k]);
					sysCodeModel.setCodeMemo(req.getParameterValues("codeMemo")[k]);

					if (req.getParameter("trxnMode").equals("list_insert")) {
						sysCodeService.insert_sysCode(sysCodeModel);
					}

					if (req.getParameter("trxnMode").equals("list_update")) {
						sysCodeService.insert_sysCode(sysCodeModel);
					}

					if (req.getParameter("trxnMode").equals("list_delete")) {
						sysCodeService.delete_sysCode(sysCodeModel);
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

	// 공통코드 생성
	@Transactional
	@ResponseBody
	@RequestMapping("sysCode/sysCodeScriptGen")
	public HashMap<String, Object> sysCodeScriptGen(HttpServletRequest req) throws Exception {

		// ------------------------------                                     
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------                                    
		HashMap<String, Object> data = new HashMap<>();
		
		SysCodeDataGen.proc_SysCodeData(); 
		
		data.put("rsMsg", "SUCCESS");
		
		return data;
	}
	
	
}