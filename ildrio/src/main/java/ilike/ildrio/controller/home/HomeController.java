package ilike.ildrio.controller.home;

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
public class HomeController {

	@Autowired
	SysMenuService sysMenuService;

	@RequestMapping("index")
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

		return "index";
	}
	
	@RequestMapping("home")
	public String frontHome(SysMenuModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "front/home/home";
	}


}