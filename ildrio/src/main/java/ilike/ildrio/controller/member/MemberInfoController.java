package ilike.ildrio.controller.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.common.EncryptionUtils;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.member.MemberInfoService;

@Controller
public class MemberInfoController {

	@Autowired
	CommonService commonService;

	@Autowired
	MemberInfoService memberInfoService;

	@RequestMapping("memberInfo/memberInfoGrid")
	public String memberInfoGrid(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/member/memberInfo/memberInfoGrid";
	}

	@RequestMapping("memberInfo/memberInfoList")
	public String memberInfoList(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/member/memberInfo/memberInfoList";
	}

	@ResponseBody
	@RequestMapping("memberInfo/memberInfoListAjax")
	public HashMap<String, Object> memberInfoListAjax(MemberInfoModel reqModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		int iPageBlock = 10;
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20"));
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
		int iStartRows = (iCurrPage - 1) * iPageRow;

		reqModel.setStartRow(String.valueOf(iStartRows));
		reqModel.setPageRow(String.valueOf(iPageRow));
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), ""));

		Map<String, Object> rsData = memberInfoService.getListMap_memberInfo(reqModel);
		List<MemberInfoModel> rsList = (List<MemberInfoModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}

	@ResponseBody
	@RequestMapping("memberInfo/memberInfoSubListAjax")
	public HashMap<String, Object> memberInfoSubListAjax(MemberInfoModel reqModel, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		List<MemberInfoModel> rsList = memberInfoService.subList_memberInfo(reqModel);

		data.put("rsList", rsList);

		return data;
	}

	@Transactional
	@ResponseBody
	@RequestMapping("memberInfo/memberInfoListTrxn")
	public HashMap<String, Object> memberInfoListTrxn(HttpServletRequest req) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------
		HashMap<String, Object> data = new HashMap<>();
		try {

			MemberInfoModel reqModel = new MemberInfoModel();
			reqModel.setTrxnMode(req.getParameter("trxnMode"));

			int irow = req.getParameterValues("chkbFlag").length;
			for (int k = 0; k < irow; k++) {

				String chkbFlag = req.getParameterValues("chkbFlag")[k];

				if (chkbFlag.equals("true")) {

					// 키컬럼
					reqModel.setMemberNo(req.getParameterValues("memberNo")[k]);

					// 리스트 수정
					if (req.getParameter("trxnMode").equals("list_update")) {
						// 수정컬럼
						memberInfoService.update_memberInfo(reqModel);
					}

					// 리스트 삭제
					if (req.getParameter("trxnMode").equals("list_delete")) {
						memberInfoService.delete_memberInfo(reqModel);
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

	@RequestMapping("memberInfo/memberInfoReg")
	public String memberInfoReg(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		MemberInfoModel rsModel = (MemberInfoModel) memberInfoService.getMap_memberInfo(reqModel);
		// pk컬럼
		if (StringUtil.NVL(rsModel.getMemberNo()).equals("")) {
			rsModel.setMemberNo(String.valueOf(commonService.sequence_common()));
			rsModel.setTrxnMode("insert");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/member/memberInfo/memberInfoReg";
	}

	@RequestMapping("memberInfo/memberInfoEdit")
	public String memberInfoEdit(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		MemberInfoModel rsModel = (MemberInfoModel) memberInfoService.getMap_memberInfo(reqModel);
		// pk컬럼
		if (StringUtil.NVL(rsModel.getMemberNo()).equals("")) {
			rsModel.setMemberNo(String.valueOf(commonService.sequence_common()));
			rsModel.setTrxnMode("insert");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/member/memberInfo/memberInfoEdit";
	}

	@RequestMapping("member/join")
	public String memberInfoNew(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		MemberInfoModel rsModel = (MemberInfoModel) memberInfoService.getMap_memberInfo(reqModel);
		// pk컬럼
		if (StringUtil.NVL(rsModel.getMemberNo()).equals("")) {
			rsModel.setMemberNo(String.valueOf(commonService.sequence_common()));
			rsModel.setTrxnMode("insert");
		}

		model.addAttribute("rsModel", rsModel);

		return "back/member/memberInfo/memberInfoNew";
	}

	@RequestMapping("member/login")
	public String memberLogin(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		String pageUrl = StringUtil.NVLS(req.getParameter("pageUrl"), "");
		model.addAttribute("pageUrl", StringUtil.NVLS(req.getParameter("pageUrl"), ""));

		String rteurnUrl = "back/member/memberInfo/memberLogin";
		// 자동로그인
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie tempCookie : cookies) {

				System.out.println(tempCookie.getName() + "/" + tempCookie.getValue());
				if (tempCookie.getName().equals("loginCookie")) {
					// 실행흐름이 서버에 있을때 서버코드로써 강제이동한다.
					// 특정 page로 이동하라는 정보만 준다.
					System.out.println("자동로그인 >> " + tempCookie.getName() + "/" + tempCookie.getValue());

					// 계정정보 확인
					MemberInfoModel sessionModel = new MemberInfoModel();
					sessionModel.setCookieSession(tempCookie.getValue());
					MemberInfoModel loginMap = (MemberInfoModel) memberInfoService.getMap_memberInfo(sessionModel);
					if (!StringUtil.NVL(loginMap.getMemberId()).equals("")) {
						// 세션 생성
						httpSession.setAttribute("ssMemberId", loginMap.getMemberId());
						httpSession.setAttribute("ssMemberName", loginMap.getMemberName());

						// 세션맵 변경
						ssMap = SessionParam.getSessionMap(httpSession);
						// redirect
						rteurnUrl = "redirect:/home";

					}
				}
			}
		}
		model.addAttribute("ssMap", ssMap);

		httpSession.setAttribute("pageUrl", pageUrl);

		// model.addAttribute("naverApiUrl",
		// naverLoginUtil.naverApiUrl(ssMap.get("ssShopId")));

		// model.addAttribute("kakaoApiUrl",
		// kakaoLoginUtil.kakaoApiUrl(ssMap.get("ssShopId")));

		return rteurnUrl;
	}

	@RequestMapping("member/logout")
	public String memberLogout(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------

		Cookie[] cookies = req.getCookies();
		System.out.println(">>>>>>>>>>>>>" + cookies.toString());

		if (cookies != null) {
			for (Cookie tempCookie : cookies) {

				System.out.println(">>>>>>>>>>>>>" + tempCookie.getName() + "/" + tempCookie.getValue());
				if (tempCookie.getName().equals("loginCookie")) {

					MemberInfoModel sessionModel = new MemberInfoModel();
					sessionModel.setCookieSession(tempCookie.getValue());
					// loginService.update_lastLogout(sessionModel);

					tempCookie.setMaxAge(0);
					response.addCookie(tempCookie);
				}
			}
		}

		// 로그아웃
		httpSession.setAttribute("ssMemberId", "");
		httpSession.setAttribute("ssMemberName", "");

		return "redirect:/home";
	}

	@RequestMapping("memberInfo/memberFindId")
	public String memberFindId(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/member/memberInfo/memberFindId";
	}

	@RequestMapping("memberInfo/memberFindPw")
	public String memberFindPw(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		return "back/member/memberInfo/memberFindPw";
	}

	@Transactional
	@ResponseBody
	@RequestMapping("memberInfo/memberInfoTrxn")
	public HashMap<String, Object> memberInfoTrxn(MemberInfoModel reqModel, HttpServletRequest req) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------
		HashMap<String, Object> data = new HashMap<>();
		try {

			data.put("rsMsg", "FAIL");
			// 입력
			if (req.getParameter("trxnMode").equals("insert")) {

				int checkCount = memberInfoService.getCount_memberInfo(reqModel);
				if (checkCount > 0) {
					data.put("rsMsg", "DUPLICATE");
				} else {
					memberInfoService.insert_memberInfo(reqModel);
					data.put("rsMsg", "등록하였습니다.");
				}
			}
			// 수정
			if (req.getParameter("trxnMode").equals("update")) {
				memberInfoService.update_memberInfo(reqModel);
				data.put("rsMsg", "수정하였습니다.");
			}

			// 삭제
			if (req.getParameter("trxnMode").equals("delete")) {
				memberInfoService.delete_memberInfo(reqModel);
				data.put("rsMsg", "삭제하였습니다.");
			}

		} catch (Exception e) {
			System.out.println(e);
			data.put("rsMsg", "FAIL");
		}
		return data;
	}

	@RequestMapping("memberInfo/memberInfoView")
	public String memberInfoView(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		MemberInfoModel rsModel = (MemberInfoModel) memberInfoService.getMap_memberInfo(reqModel);

		model.addAttribute("rsModel", rsModel);

		return "back/member/memberInfo/memberInfoView";
	}

	@PostMapping("member/loginProcess")
	@ResponseBody
	public Map<String, Object> loginProcess(HttpServletRequest req, HttpServletResponse response) throws Exception {

		// ------------------------------
		HttpSession httpSession = req.getSession();
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		// -------------------------------

		String chkProc = "T";

		// 입력한 계정정보 출력
		String pageUrl = StringUtil.NVL(req.getParameter("pageUrl"));
		String memberId = StringUtil.NVL(req.getParameter("memberId"));
		String memberPassword = StringUtil.NVL(req.getParameter("memberPassword"));
		String autologin = StringUtil.NVL(req.getParameter("autologin"));

		String enc_password = StringUtil.NVL(EncryptionUtils.encryptSha256(memberPassword));

		HashMap<String, Object> respData = new HashMap<>();

		// 계정정보 확인
		MemberInfoModel model = new MemberInfoModel();
		model.setMemberId(memberId);
		model.setMemberPassword(memberPassword);
		model.setSelectMode("login");
		MemberInfoModel loginMap = memberInfoService.getMap_memberInfo(model);

		if (StringUtil.NVL(loginMap.getMemberId()).equals("")) {
			respData.put("rsMsg", "등록된 아이디가 없습니다.");
			chkProc = "F";
		}
		if (chkProc.equals("T")) {

			if (!loginMap.getMemberPassword().equals(memberPassword)) {
				respData.put("rsMsg", "비밀번호가 잘못되었습니다.");
				chkProc = "F";
			}

		}
		if (chkProc.equals("T")) {
			// 암호화된 비밀번호 문자열 출력
			String get_password = loginMap.getMemberPassword();

			respData.put("rsMsg", "SUCCESS");

			respData.put("rtnUrl", pageUrl);

			if (autologin.equals("Y")) {

				// 쿠키에저장
				String cookieSession = httpSession.getId();
				Cookie cookie = new Cookie("loginCookie", cookieSession);
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(cookie);

				// 세션정보 업데이트
				model.setMemberId(memberId);
				model.setCookieSession(cookieSession);

				respData.put("rsMsg", "SUCCESS");
			}

			// 세션 생성
			httpSession.setAttribute("ssMemberNo", loginMap.getMemberNo());
			httpSession.setAttribute("ssMemberId", loginMap.getMemberId());
			httpSession.setAttribute("ssMemberName", loginMap.getMemberName());

			// 세션맵 변경
			ssMap = SessionParam.getSessionMap(httpSession);

		}

		return respData;
	}
	
	@RequestMapping("memberInfo/myPage")
	public String myPage(MemberInfoModel reqModel, HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// ------------------------------
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("ssMenuCode", StringUtil.NVLS(req.getParameter("menuCode"), ""));
		Map<String, String> ssMap = SessionParam.getSessionMap(httpSession);
		model.addAttribute("ssMap", ssMap);
		model.addAttribute("reqModel", reqModel);
		// -------------------------------

		MemberInfoModel rsModel = (MemberInfoModel) memberInfoService.getMap_memberInfo(reqModel);

		model.addAttribute("rsModel", rsModel);

		return "back/member/memberInfo/myPage";
	}

}