package ilike.ildrio.controller.sys;

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

import ilike.ildrio.model.sys.SysMenuModel;
import ilike.ildrio.service.sys.SysMenuService;

@Controller
public class SysMenuController {

	@Autowired
	SysMenuService sysMenuService;

	@RequestMapping("sysMenu/sysMenuGrid")
	public String sysMenuGrid(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/sys/sysMenu/sysMenuGrid";
	}

	@RequestMapping("sysMenu/sysMenuList")
	public String sysMenuList(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/sys/sysMenu/sysMenuList";
	}

	@ResponseBody
	@RequestMapping("sysMenu/sysMenuListAjax")
	public HashMap<String, Object> sysMenuListAjax(SysMenuModel reqModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		int iPageBlock = 10;
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20"));
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
		int iStartRows = (iCurrPage - 1) * iPageRow;

		reqModel.setStartRow(String.valueOf(iStartRows));
		reqModel.setPageRow(String.valueOf(iPageRow));
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), ""));

		Map<String, Object> rsData = sysMenuService.getListMap_sysMenu(reqModel);
		List<SysMenuModel> rsList = (List<SysMenuModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}

	@ResponseBody
	@RequestMapping("sysMenu/sysMenuSubListAjax")
	public HashMap<String, Object> sysMenuSubListAjax(SysMenuModel reqModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		List<SysMenuModel> rsList = sysMenuService.subList_sysMenu(reqModel);

		data.put("rsList", rsList);

		return data;
	}

	@Transactional
	@ResponseBody
	@RequestMapping("sysMenu/sysMenuListTrxn")
	public HashMap<String, Object> sysMenuListTrxn(HttpServletRequest req) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------
		HashMap<String, Object> data = new HashMap<>();
		try {

			SysMenuModel reqModel = new SysMenuModel();
			reqModel.setTrxnMode(req.getParameter("trxnMode"));

			int irow = req.getParameterValues("chkbFlag").length;
			for (int k = 0; k < irow; k++) {

				String chkbFlag = req.getParameterValues("chkbFlag")[k];

				if (chkbFlag.equals("true")) {

					// 키컬럼
					reqModel.setGroupCode(req.getParameterValues("groupCode")[k]);
					reqModel.setMenuCode(req.getParameterValues("menuCode")[k]);

					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) {
						// 수정컬럼
						reqModel.setMenuSort(req.getParameterValues("menuSort")[k]);
						reqModel.setMenuName(req.getParameterValues("menuName")[k]);
						reqModel.setMenuIcon(req.getParameterValues("menuIcon")[k]);
						reqModel.setLinkUrl(req.getParameterValues("linkUrl")[k]);
						reqModel.setDefaultCode(req.getParameterValues("defaultCode")[k]);
						reqModel.setTargetType(req.getParameterValues("targetType")[k]);
						reqModel.setUseYn(req.getParameterValues("useYn")[k]);
						sysMenuService.update_sysMenu(reqModel);
					}

					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) {
						sysMenuService.delete_sysMenu(reqModel);
					}
				}
			}

			// 경료업데이트
			reqModel.setTrxnMode("update_path");
			sysMenuService.update_sysMenu(reqModel);

			data.put("rsMsg", "SUCCESS");

		} catch (Exception e) {
			System.out.println(e);
			data.put("rsMsg", "FAIL");
		}
		return data;
	}

	@RequestMapping("sysMenu/sysMenuReg")
	public String sysMenuReg(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		SysMenuModel rsModel = (SysMenuModel) sysMenuService.getMap_sysMenu(reqModel);
		// pk컬럼
		if (StringUtil.NVL(rsModel.getMenuCode()).equals("")) {
			rsModel.setGroupCode(reqModel.getGroupCode());
			rsModel.setMenuCode(reqModel.getMenuCode());
			rsModel.setTrxnMode("insert");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/sys/sysMenu/sysMenuReg";
	}

	@RequestMapping("sysMenu/sysMenuSubReg")
	public String sysMenuSubReg(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		SysMenuModel rsModel = (SysMenuModel) sysMenuService.getMap_sysMenu(reqModel);

		reqModel.setTrxnMode("select_newCode");
		SysMenuModel subModel = (SysMenuModel) sysMenuService.getMap_sysMenu(reqModel);
		// pk컬럼

		rsModel.setGroupCode(reqModel.getGroupCode());
		rsModel.setMenuNewCode(subModel.getMenuNewCode());
		rsModel.setMenuSort(reqModel.getMenuSort());
		rsModel.setTrxnMode("insert");
		
		if(rsModel.getMenuLevel().equals("0")) {
			rsModel.setMenuCode("0");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/sys/sysMenu/sysMenuSubReg";
	}

	@Transactional
	@ResponseBody
	@RequestMapping("sysMenu/sysMenuTrxn")
	public HashMap<String, Object> sysMenuTrxn(SysMenuModel reqModel, HttpServletRequest req) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------
		HashMap<String, Object> data = new HashMap<>();
		try {

			data.put("rsMsg", "FAIL");
			// 입력
			if (req.getParameter("trxnMode").equals("insert")) {

				int checkCount = sysMenuService.getCount_sysMenu(reqModel);
				if (checkCount > 0) {
					data.put("rsMsg", "DUPLICATE");
				} else {
					//뒤로밀기
					reqModel.setTrxnMode("update_moveNext_for_insert");
					sysMenuService.update_sysMenu(reqModel);
					//입력
					sysMenuService.insert_sysMenu(reqModel);
					data.put("rsMsg", "등록하였습니다.");
				}
			}
			// 수정
			if (req.getParameter("trxnMode").equals("update")) {
				
				// 절렬순서 앞으로 이동
				if ( Integer.parseInt(reqModel.getNowSort()) >  Integer.parseInt(reqModel.getMenuSort()) ) {
					reqModel.setTrxnMode("update_movePrev");
					sysMenuService.update_sysMenu(reqModel);
				}
				// 정렬순서 뒤로 이동
				if ( Integer.parseInt(reqModel.getNowSort()) <  Integer.parseInt(reqModel.getMenuSort()) ) {
					reqModel.setTrxnMode("update_moveNext");
					sysMenuService.update_sysMenu(reqModel);
				}
				reqModel.setTrxnMode("");
				sysMenuService.update_sysMenu(reqModel);
				data.put("rsMsg", "수정하였습니다.");
			}

			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) {
				sysMenuService.delete_sysMenu(reqModel);
				data.put("rsMsg", "삭제하였습니다.");
			}
			
			

			// 경료업데이트
			reqModel.setTrxnMode("update_path");
			sysMenuService.update_sysMenu(reqModel);

		} catch (Exception e) {
			System.out.println(e);
			data.put("rsMsg", "FAIL");
		}
		return data;
	}

	@RequestMapping("sysMenu/sysMenuView")
	public String sysMenuView(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		SysMenuModel rsModel = (SysMenuModel) sysMenuService.getMap_sysMenu(reqModel);

		model.addAttribute("rsModel", rsModel);

		return "back/sys/sysMenu/sysMenuView";
	}

}