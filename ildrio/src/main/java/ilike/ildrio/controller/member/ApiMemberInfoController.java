package ilike.ildrio.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;
import ilike.ildrio.config.security.JwtTokenProvider;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.service.member.MemberInfoService;

@RestController
@RequestMapping("/api")
public class ApiMemberInfoController {

	@Autowired
	CommonService commonService;

	@Autowired
	MemberInfoService memberInfoService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@ResponseBody
	@RequestMapping("/apiMemberInfoList/{selectMode}")
	public HashMap<String, Object> apiMemberInfoList(MemberInfoModel reqModel, @PathVariable String selectMode, HttpServletRequest req) throws Exception {
		
		
		String manpowerCompanyId = "";
		String bearerToken = req.getHeader("Authorization");
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			manpowerCompanyId = jwtTokenProvider.getUsername(token);
			System.out.println("Member Info Fetched - manpowerCompanyId: " + manpowerCompanyId);
		}
		reqModel.setManpowerCompanyId(manpowerCompanyId);
		
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		int iPageBlock = 10; // 페이지 블록 크기 (클라이언트에서 사용하지 않더라도 유지)
		int iPageRow = 3; // 페이지당 행 수를 3으로 고정
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1")); // 현재 페이지, 기본값 1
		int iStartRows = (iCurrPage - 1) * iPageRow; // 시작 행 계산

		// 요청 모델에 페이징 파라미터 설정
		reqModel.setStartRow(String.valueOf(iStartRows));
		reqModel.setPageRow(String.valueOf(iPageRow));

		// 검색 조건 설정
		reqModel.setSelectMode(selectMode);
		reqModel.setMemberName(StringUtil.NVLS(req.getParameter("memberName"), ""));
		reqModel.setMobileNo(StringUtil.NVLS(req.getParameter("mobileNo"), ""));
		reqModel.setBirthDate(StringUtil.NVLS(req.getParameter("birthDate"), ""));
		reqModel.setPostalAddress(StringUtil.NVLS(req.getParameter("postalAddress"), ""));
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), ""));

		// 서비스 호출하여 데이터 조회
		Map<String, Object> rsData = memberInfoService.getListMap_memberInfo(reqModel);
		List<MemberInfoModel> rsList = (List<MemberInfoModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");

		// 응답 데이터 구성
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));
		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}
	
	
	@ResponseBody
	@RequestMapping("/memberDetail/{memberId}")
	public HashMap<String, Object> apiMemberDetail(@PathVariable String memberId, HttpServletRequest req) {
	  
	  HashMap<String, Object> data = new HashMap<>();
	  
	  MemberInfoModel reqModel= new MemberInfoModel();
	  reqModel.setMemberId(memberId);
	  
	  MemberInfoModel member = memberInfoService.getMap_memberInfo(reqModel);
	  data.put("rsData", member); // rsData 키로 감싸기
	  
	  return data;
	}
}