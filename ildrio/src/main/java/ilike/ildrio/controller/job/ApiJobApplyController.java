package ilike.ildrio.controller.job;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.config.security.JwtTokenProvider;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.model.job.JobApplyModel;
import ilike.ildrio.service.job.JobApplyService;

@RestController
public class ApiJobApplyController {

	@Autowired
	JobApplyService jobApplyService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@ResponseBody
	@RequestMapping("/api/apiJobApplyList")
	public HashMap<String, Object> apiJobApplyList(JobApplyModel reqModel, HttpServletRequest req) throws Exception {

		System.out.println(reqModel.toString());
		
		System.out.println(req.getParameter("selectMode"));
		
		String selectMode = StringUtil.NVL(req.getParameter("selectMode"));
	
		
		String memberId = "";
		String bearerToken = req.getHeader("Authorization");
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			memberId = jwtTokenProvider.getUsername(token);
			System.out.println("Member Info Fetched - MemberId: " + memberId);
		}
		reqModel.setMemberId(memberId);
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		int iPageBlock = 10;
		int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20"));
		int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
		int iStartRows = (iCurrPage - 1) * iPageRow;

		reqModel.setSelectMode(selectMode);
		reqModel.setStartRow(String.valueOf(iStartRows));
		reqModel.setPageRow(String.valueOf(iPageRow));
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "jap.job_no desc,jmp.manpower_no asc "));

		if(selectMode.equals("myToday")) {
			reqModel.setMemberId(memberId);
		}
		if(selectMode.equals("customerToday")) {
			reqModel.setCustomerId(memberId);
		}
		if(selectMode.equals("adminToday")) {
			reqModel.setCompanyId(memberId);
		}
		
		Map<String, Object> rsData = jobApplyService.getListMap_jobApply(reqModel);
		List<JobApplyModel> rsList = (List<JobApplyModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}
	
	@ResponseBody
	@RequestMapping("/api/apiJobApplyListJobNo/{jobNo}")
	public HashMap<String, Object> apiJobApplyListJobNo(JobApplyModel reqModel,@PathVariable String jobNo, HttpServletRequest req) throws Exception {

		System.out.println(reqModel.toString());
		
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

	@PostMapping("/api/apiJobApplyInsert/{jobNo}/{manpowerNo}")
	public ResponseEntity<?> apiJobApplyTrxn(@RequestBody JobApplyModel reqModel, HttpServletRequest req, @PathVariable String jobNo, @PathVariable String manpowerNo) {

		Map<String, Object> response = new HashMap<>();

		try {

			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);

				reqModel.setManpowerNo(manpowerNo);
				reqModel.setMemberId(memberId);
				reqModel.setJobNo(jobNo);

				int checkCount = jobApplyService.getCount_jobApply(reqModel);
				if (checkCount > 0) {
					response.put("message", "이미 지원한 정보가 있습니다.");
				} else {
					jobApplyService.insert_jobApply(reqModel);
					response.put("message", "지원하였습니다.");
				}
				System.out.println(">>>" + checkCount);

			} else {
				System.out.println("MemberInfo - Invalid or Missing Token");
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("message", "실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PostMapping("/api/apiJobApplyCancel/{jobNo}/{manpowerNo}")
	public ResponseEntity<?> apiJobApplyCancel(@RequestBody JobApplyModel reqModel, HttpServletRequest req, @PathVariable String jobNo, @PathVariable String manpowerNo) {

		Map<String, Object> response = new HashMap<>();

		try {

			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);

				reqModel.setManpowerNo(manpowerNo);
				reqModel.setMemberId(memberId);
				reqModel.setJobNo(jobNo);

				jobApplyService.delete_jobApply(reqModel);

				return ResponseEntity.ok(response);
			} else {
				response.put("message", "실패");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		} catch (Exception e) {
			response.put("message", "실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}
}