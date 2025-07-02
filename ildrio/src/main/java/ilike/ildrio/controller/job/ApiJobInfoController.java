package ilike.ildrio.controller.job;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ilike.ildrio.config.security.JwtTokenProvider;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.model.member.MemberInfoModel;

import ilike.ildrio.model.job.JobInfoModel;
import ilike.ildrio.service.job.JobInfoService;

import ilike.ildrio.model.job.JobManpowerModel;
import ilike.ildrio.service.job.JobManpowerService;

@RestController
public class ApiJobInfoController {

	@Autowired
	CommonService commonService;

	@Autowired
	JobInfoService jobInfoService;

	@Autowired
	JobManpowerService jobManpowerService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@ResponseBody
	@GetMapping("/api/jobInfoListAjax")
	public HashMap<String, Object> apiJobInfoListAjax(JobInfoModel reqModel, HttpServletRequest req) throws Exception {
	    HashMap<String, Object> data = new HashMap<String, Object>();
	    
	 // 모든 파라미터 출력
	    System.out.println("All Request Parameters: " + req.getParameterMap());
	    System.out.println("jobTitle ::: " + req.getParameter("jobTitle"));
	    System.out.println("workDate ::: " + req.getParameter("workDate"));
	    System.out.println("techCode ::: " + req.getParameter("techCode"));
	    System.out.println("careerCode ::: " + req.getParameter("careerCode"));
	    System.out.println("startRow ::: " + req.getParameter("startRow"));
	    System.out.println("pageRow ::: " + req.getParameter("pageRow"));

	    int iPageBlock = 10; // 페이지 블록 크기 (10개씩 표시)
	    int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "3")); // 기본값 3으로 변경
	    int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
	    int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
	    int iStartRows = (iCurrPage - 1) * iPageRow;

	    // 프론트엔드에서 전달된 startRow가 있으면 우선 사용
	    String startRowParam = req.getParameter("startRow");
	    if (startRowParam != null && !startRowParam.isEmpty()) {
	        iStartRows = Integer.parseInt(startRowParam);
	    }

	    reqModel.setStartRow(String.valueOf(iStartRows));
	    reqModel.setPageRow(String.valueOf(iPageRow));
	    reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "job_no desc"));

	    // jobTitle 검색 조건 추가
	    String jobTitle = req.getParameter("jobTitle");
	    if (jobTitle != null && !jobTitle.isEmpty()) {
	        reqModel.setJobTitle(jobTitle);
	    }

	    Map<String, Object> rsData = jobInfoService.getListMap_jobInfo(reqModel);
	    List<JobInfoModel> rsList = (List<JobInfoModel>) rsData.get("rsList");
	    int totRow = (int) rsData.get("totRow");
	    data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

	    data.put("rsList", rsList);
	    data.put("totRow", totRow);

	    // JWT 토큰 확인 및 사용자 정보 추가
	    String bearerToken = req.getHeader("Authorization");
	    String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
	    if (token != null && jwtTokenProvider.validateToken(token)) {
	        String memberId = jwtTokenProvider.getUsername(token);
	        System.out.println("Member Info Fetched - MemberId: " + memberId);
	    } else {
	        System.out.println("MemberInfo - Invalid or Missing Token");
	    }

	    return data;
	}
	
	@ResponseBody
	@RequestMapping("/api/jobInfoListAjax/{selectMode}")
	public HashMap<String, Object> apiJobInfoListAjax2(JobInfoModel reqModel, @PathVariable String selectMode, HttpServletRequest req) throws Exception {
	    HashMap<String, Object> data = new HashMap<String, Object>();
	    
	    // JWT 토큰 확인 및 사용자 정보 추가
	    String bearerToken = req.getHeader("Authorization");
	    String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
	    String memberId = "";
	    if (token != null && jwtTokenProvider.validateToken(token)) {
	        memberId = jwtTokenProvider.getUsername(token);
	        System.out.println("Member Info Fetched - MemberId: " + memberId);
	    } else {
	        System.out.println("MemberInfo - Invalid or Missing Token");
	    }
	    
	    int iPageBlock = 10; // 페이지 블록 크기 (10개씩 표시)
	    int iPageRow = Integer.parseInt(StringUtil.NVLS(req.getParameter("pageRow"), "20")); // 기본값 3으로 변경
	    int iCurrPage = Integer.parseInt(StringUtil.NVLS(req.getParameter("currPage"), "1"));
	    int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);
	    int iStartRows = (iCurrPage - 1) * iPageRow;

	    // 프론트엔드에서 전달된 startRow가 있으면 우선 사용
	    String startRowParam = req.getParameter("startRow");
	    if (startRowParam != null && !startRowParam.isEmpty()) {
	        iStartRows = Integer.parseInt(startRowParam);
	    }
	    
	    

	   
	    reqModel.setSelectMode(StringUtil.NVLS(selectMode,"worker"));
	    reqModel.setStartRow(String.valueOf(iStartRows));
	    reqModel.setPageRow(String.valueOf(iPageRow));
	    reqModel.setSrhOrderBy(StringUtil.NVLS(req.getParameter("srhOrderBy"), "job_no desc"));

	    // jobTitle 검색 조건 추가
	    String jobTitle = req.getParameter("jobTitle");
	    if (jobTitle != null && !jobTitle.isEmpty()) {
	        reqModel.setJobTitle(jobTitle);
	    }
	    
	    if(selectMode.equals("admin")) {
	    	 reqModel.setCompanyId(memberId);
	    }
	    if(selectMode.equals("customer")) {
	    	 reqModel.setCustomerId(memberId);
	    }

	    Map<String, Object> rsData = jobInfoService.getListMap_jobInfo(reqModel);
	    List<JobInfoModel> rsList = (List<JobInfoModel>) rsData.get("rsList");
	    int totRow = (int) rsData.get("totRow");
	    data.put("gridPageNavigation", StringUtil.getGridPageNavigation(totRow, iPageRow, iCurrPage, iPageBlock));

	    data.put("rsList", rsList);
	    data.put("totRow", totRow);

	   

	    return data;
	}

	@ResponseBody
	@RequestMapping("/api/jobInfo/{jobNo}")
	public HashMap<String, Object> apiJobInfo(JobInfoModel reqModel, @PathVariable String jobNo, HttpServletRequest req) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		reqModel.setJobNo(jobNo);

		JobInfoModel rsData = jobInfoService.getMap_jobInfo(reqModel);
		data.put("rsData", rsData);

		

		return data;
	}
	
	
	@PostMapping("/api/job-copy/{jobNo}")
	public ResponseEntity<Map<String, String>> apiJobInfoCopy(JobInfoModel reqModel, @PathVariable String jobNo, HttpServletRequest req) throws Exception {

		// JWT 토큰 확인 및 사용자 정보 추가
		String memberId = "";
		String bearerToken = req.getHeader("Authorization");
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			memberId = jwtTokenProvider.getUsername(token);
			System.out.println("Member Info Fetched - MemberId: " + memberId);
		} else {
			System.out.println("MemberInfo - Invalid or Missing Token");
		}

		String newJobNo = SessionParam.getNewId("J");
		reqModel.setJobNo(jobNo);
		reqModel.setNewJobNo(newJobNo);
		reqModel.setCustomerId(memberId);
		
		System.out.println("newJobNo >>>> "+newJobNo);

		jobInfoService.insert_copy_jobInfo(reqModel);
		
		
		JobManpowerModel jobManpowerModel = new JobManpowerModel();
		jobManpowerModel.setJobNo(jobNo);
		jobManpowerModel.setNewJobNo(newJobNo);
		jobManpowerService.insert_copy_jobManpower(jobManpowerModel);

		// JobInfo 저장 로직 (예: DB insert)
		Map<String, String> response = new HashMap<>();
		response.put("newJobNo", newJobNo);

		return ResponseEntity.ok().body(response);
		
	}

	@ResponseBody
	@RequestMapping("/api/job-apply")
	public HashMap<String, Object> apiJobApply(@RequestBody Map<String, String> request, HttpSession session) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		System.out.println(request.toString());

		return data;
	}

	@PostMapping("/api/job-register")
	public ResponseEntity<Map<String, String>> registerJob(@RequestBody JobInfoModel reqModel, HttpServletRequest req) throws Exception {

		// JWT 토큰 확인 및 사용자 정보 추가
		String memberId = "";
		String bearerToken = req.getHeader("Authorization");
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			memberId = jwtTokenProvider.getUsername(token);
			System.out.println("Member Info Fetched - MemberId: " + memberId);
		} else {
			System.out.println("MemberInfo - Invalid or Missing Token");
		}

		String jobNo = SessionParam.getNewId("J");

		reqModel.setJobNo(jobNo);
		reqModel.setCustomerId(memberId);
		reqModel.setBringThings(String.join(",", reqModel.getBringThingList()));

		System.out.println(reqModel.toString());

		jobInfoService.insert_jobInfo(reqModel);

		// JobInfo 저장 로직 (예: DB insert)
		Map<String, String> response = new HashMap<>();
		response.put("jobNo", jobNo);

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/api/job-update")
	public ResponseEntity<Map<String, String>> jobUpdate(@RequestBody JobInfoModel reqModel, HttpServletRequest req) throws Exception {

		Map<String, String> response = new HashMap<>();

		try {
			// JWT 토큰 확인 및 사용자 정보 추가
			String memberId = "";
			String bearerToken = req.getHeader("Authorization");
			String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
			if (token != null && jwtTokenProvider.validateToken(token)) {
				memberId = jwtTokenProvider.getUsername(token);
				System.out.println("Member Info Fetched - MemberId: " + memberId);
			} else {
				System.out.println("MemberInfo - Invalid or Missing Token");
			}

			reqModel.setBringThings(String.join(",", reqModel.getBringThingList()));

			System.out.println(reqModel.toString());

			jobInfoService.update_jobInfo(reqModel);

			response.put("message", "수정하였습니다.");
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.put("message", "저장 실패: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	// JobManpower ====================================
	@ResponseBody
	@RequestMapping("/api/job-manpower-list/{jobNo}")
	public HashMap<String, Object> apiJobManpowerList(@RequestBody JobManpowerModel reqModel, HttpServletRequest req,@PathVariable String jobNo) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		System.out.println(jobNo);

		// JWT 토큰 확인 및 사용자 정보 추가
		String memberId = "";
		String bearerToken = req.getHeader("Authorization");
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			memberId = jwtTokenProvider.getUsername(token);
			System.out.println("Member Info Fetched - MemberId: " + memberId);
		}
		reqModel.setMemberId(memberId);
		reqModel.setJobNo(jobNo);
		Map<String, Object> rsData = jobManpowerService.getListMap_jobManpower(reqModel);
		List<JobManpowerModel> rsList = (List<JobManpowerModel>) rsData.get("rsList");

		data.put("rsList", rsList);

		return data;
	}

	@ResponseBody
	@GetMapping("/api/job-manpower-info/{manpowerNo}")
	public HashMap<String, Object> apiJobManpowerInfo(@PathVariable String manpowerNo) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		System.out.println(manpowerNo);

		JobManpowerModel reqModel = new JobManpowerModel();
		reqModel.setManpowerNo(manpowerNo);
		JobManpowerModel rsData = jobManpowerService.getMap_jobManpower(reqModel);

		data.put("rsData", rsData);

		return data;
	}

	@PostMapping("/api/apiJobManpowerInsert")
	public ResponseEntity<?> apiJobManpowerTrxn(@RequestBody JobManpowerModel reqModel, HttpServletRequest req) {

		Map<String, Object> response = new HashMap<>();

		try {

			int checkCount = jobManpowerService.getCount_jobManpower(reqModel);
			
			
			if (checkCount > 0) {
				response.put("message", "이미 등록된 정보가 있습니다.");
			} else {
				jobManpowerService.insert_jobManpower(reqModel);
				response.put("message", "등록하였습니다.");
			}

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			System.out.println(e);
			response.put("message", "실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PostMapping("/api/apiJobManpowerUpdate")
	public ResponseEntity<?> apiJobManpowerUpdate(@RequestBody JobManpowerModel reqModel, HttpServletRequest req) {

		Map<String, Object> response = new HashMap<>();

		try {

			jobManpowerService.update_jobManpower(reqModel);
			response.put("message", "등록하였습니다.");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("message", "실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@DeleteMapping("/api/apiJobManpowerDelete/{manpowerNo}")
	public ResponseEntity<?> apiJobManpowerDelete(@PathVariable String manpowerNo) throws Exception {
		Map<String, Object> response = new HashMap<>();

		JobManpowerModel reqModel = new JobManpowerModel();
		reqModel.setManpowerNo(manpowerNo);
		jobManpowerService.delete_jobManpower(reqModel);
		response.put("message", "삭제하였습니다.");

		return ResponseEntity.ok(response);

	}

}