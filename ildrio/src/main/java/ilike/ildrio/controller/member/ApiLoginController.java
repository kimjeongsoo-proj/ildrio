package ilike.ildrio.controller.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.common.EncryptionUtils;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;
import ilike.ildrio.common.ppurio.SendMmsMessage;

import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.member.MemberInfoService;
import io.jsonwebtoken.lang.Collections;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class ApiLoginController {

	@Autowired
	CommonService commonService;

	@Autowired
	MemberInfoService memberInfoService;

	@PostMapping("/api/loginProcess")
	public ResponseEntity<?> login(@RequestBody MemberInfoModel request, HttpSession session) {

		Map<String, Object> response = new HashMap<>();

		MemberInfoModel loginMap = memberInfoService.getMap_memberInfo(request);

		if (StringUtil.NVL(loginMap.getMemberId()).equals(request.getMemberId()) && StringUtil.NVL(loginMap.getMemberPassword()).equals(request.getPassword())) {

			// 세션 생성
			session.setAttribute("ssMemberId", loginMap.getMemberId());
			session.setAttribute("ssMemberName", loginMap.getMemberName());

			response.put("message", "로그인 성공!");

			return ResponseEntity.ok().body(response);
		}

		if (StringUtil.NVL(loginMap.getMemberId()).equals("")) {
			response.put("message", "등록된 아이디가 없습니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		if (!StringUtil.NVL(loginMap.getMemberPassword()).equals(request.getPassword())) {
			response.put("message", "비밀번호가 잘못되었습니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		response.put("message", "로그인 실패");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@GetMapping("/api/memberSession")
	public ResponseEntity<?> memberSession(HttpSession session) {
		Map<String, Object> response = new HashMap<>();

		if (session.getAttribute("ssMemberId") != null) {
			response.put("memberId", session.getAttribute("ssMemberId"));
			response.put("memberName", session.getAttribute("ssMemberName"));
			return ResponseEntity.ok().body(response);
		}
		
		System.out.println(session.getAttribute("ssMemberId"));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@PostMapping("/api/register")
	public ResponseEntity<?> register(@RequestBody MemberInfoModel request, HttpSession session) {

		Map<String, Object> response = new HashMap<>();

		System.out.println(request.toString());

		MemberInfoModel rsModel = memberInfoService.getMap_memberMobileCert(request);

		if (rsModel != null) {
			if (StringUtil.NVL(rsModel.getMobileNo()).equals(request.getMobileNo())) {

				// 아이디 중복체크
				int chkCount = memberInfoService.getCount_memberInfo(request);
				if (chkCount != 0) {
					response.put("message", "이미 등록되 아이디가 있습니다.");
				} else {

					// 회원가입
					memberInfoService.insert_memberInfo(request);

					response.put("message", "회원가입이 완료 되었습니다.");
				}

			}
		} else {
			response.put("message", "인증확인 정보가 변경 되었습니다. 다시 인증해 주세요.");
		}

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/api/certNo_send")
	public ResponseEntity<?> certNo_send(@RequestBody MemberInfoModel request, HttpSession session) {

		Map<String, Object> response = new HashMap<>();

		System.out.println(request.toString());

		String certKey = StringUtil.newUUID();
		String certNo = String.valueOf(StringUtil.randomInt(1000, 9999));

		response.put("certKey", certKey);
		response.put("certNo", certNo);

		// 입력
		request.setCertKey(certKey);
		request.setCertNo(certNo);
		memberInfoService.insert_memberMobileCert(request);

		// sms 보내기
		try {

			File desti = new File("/home/ilikekim/UPLOADS");
			if (desti.exists()) {

				System.out.println(">>>>>>>>");

				// SMS전송
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("smsTelNo", request.getMobileNo());
				smsMap.put("smsMsgText", "[일드리오 회원가입 인증번호 [" + certNo + "] 입니다.");
				smsMap.put("smsSubject", certNo);
				String smsResult = SendMmsMessage.sms_ppurio(smsMap);

				if (smsResult.indexOf("ok") > -1) {
					response.put("message", "Login successful");
				} else {
					response.put("message", smsResult);
				}
			} else {
				System.out.println("LOCAL " + certNo);
				response.put("message", certNo);
			}

		} catch (Exception e) {
			response.put("message", "문자전송 실패");
		}

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/api/certNo_confirm")
	public ResponseEntity<?> certNo_confirm(@RequestBody MemberInfoModel request, HttpSession session) {

		Map<String, Object> response = new HashMap<>();

		System.out.println(request.toString());

		MemberInfoModel rsModel = memberInfoService.getMap_memberMobileCert(request);

		if (rsModel != null) {
			if (StringUtil.NVL(rsModel.getMobileNo()).equals(request.getMobileNo())) {
				response.put("message", "인증번호가 확인되었습니다.");
			}
		} else {
			response.put("message", "인증확인 실패");
		}

		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/api/upload")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
		Map<String, String> response = new HashMap<>();
		if (file.isEmpty()) {
			response.put("message", "파일이 없습니다.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		String UPLOAD_DIR = "C:/LOCAL_FILE";

		try {
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename()).toString();
			file.transferTo(new File(filePath));

			response.put("message", "파일 업로드 성공");
			response.put("fileName", file.getOriginalFilename());
			return ResponseEntity.ok(response);
		} catch (IOException e) {
			response.put("message", "파일 업로드 실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/api/member/update")
	public ResponseEntity<String> updateUserInfo(@RequestBody MemberInfoModel request, HttpSession session) {

		System.out.println(request.toString());

		return ResponseEntity.ok("회원정보 수정 성공");
	}
	
	@GetMapping("/api/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
	    session.invalidate(); // 세션 무효화
	    return ResponseEntity.noContent().build(); // ✅ 204 응답 반환 (리디렉션 X)
	}

}