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
import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.config.security.JwtTokenProvider;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;
import ilike.ildrio.common.ppurio.SendMmsMessage;
import ilike.ildrio.model.job.JobEmployModel;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.job.JobEmployService;
import ilike.ildrio.service.member.MemberInfoService;

@RestController
public class ApiJobEmployController {

	@Autowired
	JobEmployService jobEmployService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private MemberInfoService memberInfoService;
	
	@ResponseBody
	@RequestMapping("/api/apiJobEmployList")
	public HashMap<String, Object> apiJobEmployList(JobEmployModel reqModel, HttpServletRequest req) throws Exception {

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
		reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), ""));

		Map<String, Object> rsData = jobEmployService.getListMap_jobEmploy(reqModel);
		List<JobEmployModel> rsList = (List<JobEmployModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}
	
	@ResponseBody
	@RequestMapping("/api/apiJobEmployListJobNo/{jobNo}")
	public HashMap<String, Object> apiJobEmployListJobNo(JobEmployModel reqModel,@PathVariable String jobNo, HttpServletRequest req) throws Exception {

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

		Map<String, Object> rsData = jobEmployService.getListMap_jobEmploy(reqModel);
		List<JobEmployModel> rsList = (List<JobEmployModel>) rsData.get("rsList");
		int totRow = (int) rsData.get("totRow");
		data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

		data.put("rsList", rsList);
		data.put("totRow", totRow);

		return data;
	}

	@PostMapping("/api/apiJobEmployInsert/{jobApplyNo}")
	public ResponseEntity<?> apiJobEmployInsert(@RequestBody JobEmployModel reqModel, HttpServletRequest req, @PathVariable String jobApplyNo) {

		Map<String, Object> response = new HashMap<>();

		try {

			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);

				reqModel.setJobApplyNo(jobApplyNo);
	
				reqModel.setSelectMode("chkDupDate");
				int checkCount = jobEmployService.getCount_jobEmploy(reqModel);
				if (checkCount > 0) {
					response.put("message", "같은날짜에 채용된 정보가 있습니다.");
				} else {
					reqModel.setSelectMode("chkManpowerCount");
					int checkCount2 = jobEmployService.getCount_jobEmploy(reqModel);
					if (checkCount2 < 1) {
						response.put("message", "이미 채용이 완료 되았습니다.");
					} else {
						jobEmployService.insert_jobEmploy(reqModel);
						response.put("message", "채용하였습니다.");
						return ResponseEntity.ok(response);
					}
					
				}
				System.out.println(">>>" + checkCount);

			} else {
				System.out.println("MemberInfo - Invalid or Missing Token");
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		} catch (Exception e) {
			response.put("message", "실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PostMapping("/api/apiJobEmployCancel/{jobApplyNo}")
	public ResponseEntity<?> apiJobEmployCancel(@RequestBody JobEmployModel reqModel, HttpServletRequest req, @PathVariable String jobApplyNo) {

		Map<String, Object> response = new HashMap<>();
		
		String trxnType = StringUtil.NVL(req.getParameter("trxnType"));

		try {

			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);

				reqModel.setJobApplyNo(jobApplyNo);
				
				if(trxnType.equals("cancel")) {
					reqModel.setTrxnMode("cancel");
					jobEmployService.update_jobEmploy(reqModel);
				}
				if(trxnType.equals("delete")) {
					reqModel.setTrxnMode("cancel");
					jobEmployService.delete_jobEmploy(reqModel);
				}
				

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
	
	@PostMapping("/api/apiJobEmployUpdate/{jobApplyNo}")
	public ResponseEntity<?> apiJobEmployUpdate(@RequestBody JobEmployModel reqModel, HttpServletRequest req, @PathVariable String jobApplyNo) {

		Map<String, Object> response = new HashMap<>();
		
		String trxnType = StringUtil.NVL(req.getParameter("trxnType"));

		try {

			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);
				
				

				reqModel.setJobApplyNo(jobApplyNo);
				
				
				reqModel.setTrxnMode(trxnType);
				jobEmployService.update_jobEmploy(reqModel);
				
				
				//회원정보조회
				JobEmployModel memberModel = jobEmployService.getMap_jobEmploy(reqModel);

				System.out.println(">>>>>>>>");

				// SMS전송
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("smsTelNo", memberModel.getMobileNo());
				
				String smsMsgText = "";
				String smsSubject = "";
				
				if(trxnType.equals("department_req")) {
					 smsMsgText = "[일드리오 - 근무 출발확인 요청]https://일드리오.kr/job/job-today/myToday";
					 smsSubject = "근무 출발확인 요청";
				}
				
				//문자전송
				File desti = new File("/home/tae11111/UPLOADS");
				if (desti.exists()) {
					
					smsMap.put("smsMsgText", smsMsgText);
					smsMap.put("smsSubject", smsSubject);
					String smsResult = SendMmsMessage.sms_ppurio(smsMap);

					if (smsResult.indexOf("ok") > -1) {
						response.put("message", "Login successful");
					} else {
						response.put("message", smsResult);
					}
				} else {
					System.out.println(smsMsgText);

				}
				
				

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