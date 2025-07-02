package ilike.ildrio.controller.member;

import ilike.ildrio.common.StringUtil;
import ilike.ildrio.common.ppurio.SendMmsMessage;
import ilike.ildrio.config.security.JwtTokenProvider;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.service.member.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/loginProcess")
	public ResponseEntity<?> login(@RequestBody MemberInfoModel request) {
		Map<String, Object> response = new HashMap<>();
		System.out.println("Login Request 4444444: " + request);

		try {
			
			String encodedPassword = passwordEncoder.encode(request.getPassword());
			System.out.println("encodedPassword: " + encodedPassword);
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMemberId(), request.getPassword()));
			System.out.println("Authentication Success - Principal: " + authentication.getPrincipal() + ", Authorities: " + authentication.getAuthorities());

			String jwt = jwtTokenProvider.createToken(authentication);
			response.put("token", jwt);
			response.put("message", "로그인 성공!");
			System.out.println("Login Success - JWT: " + jwt);
			
			//비밀번호 업데이트
			request.setTrxnMode("update_password_dec");
			request.setMemberPasswordDec(request.getPassword());
			memberInfoService.update_memberInfo(request);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("message", "로그인 실패: " + e.getMessage());
			System.out.println("Login Failed: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody MemberInfoModel request) {
		Map<String, Object> response = new HashMap<>();
		System.out.println("Register Request: " + request);

		try {
			// 인증 확인
			MemberInfoModel rsModel = memberInfoService.getMap_memberMobileCert(request);
			if (rsModel == null || !StringUtil.NVL(rsModel.getMobileNo()).equals(request.getMobileNo())) {
				response.put("message", "인증확인 정보가 변경되었습니다. 다시 인증해 주세요.");
				return ResponseEntity.ok(response);
			}

			request.setMemberPasswordDec(request.getPassword());
			// 비밀번호 암호화
			String encodedPassword = passwordEncoder.encode(request.getPassword());
			request.setPassword(encodedPassword); // 올바른 필드에 설정
			System.out.println("Password Encoded - MemberId: " + request.getMemberId() + ", Encoded: " + encodedPassword);

			// 아이디 중복 체크
			int chkCount = memberInfoService.getCount_memberInfo(request);
			if (chkCount != 0) {
				response.put("message", "이미 등록된 아이디가 있습니다.");
				return ResponseEntity.ok(response);
			}

			request.setSelectMode("chk_name_hpno");
			int chkHpnoCount = memberInfoService.getCount_memberInfo(request);
			if (chkHpnoCount != 0) {
				response.put("message", "이미 등록된 성명과 핸드폰번호가 있습니다.");
				return ResponseEntity.ok(response);
			}

			// 회원 정보 삽입
			memberInfoService.insert_memberInfo(request);

			// JWT 생성 없이 성공 메시지만 반환
			response.put("memberId", request.getMemberId());
			response.put("message", "회원가입이 완료되었습니다.");
			System.out.println("Register Success");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("message", "회원가입 실패: " + e.getMessage());
			System.out.println("Register Failed: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/certNo_send")
	public ResponseEntity<?> sendCertNo(@RequestBody MemberInfoModel request) {
		Map<String, Object> response = new HashMap<>();

		
		System.out.println("getTrxnMode >>>>>>>>"+request.getTrxnMode());
		
		if("chkMember".equals(request.getTrxnMode())) {
			
			request.setSelectMode("chk_name_hpno");
			int chkHpnoCount = memberInfoService.getCount_memberInfo(request);
			System.out.println("chkHpnoCount >>>>>>>> "+chkHpnoCount);
			if (chkHpnoCount == 0) {
				response.put("message", "등록된 성명과 핸드폰번호가 없습니다.");
				return ResponseEntity.ok(response);
			}
		}
		
		// 여기서 실제 SMS 발송 로직을 구현해야 함
		String certKey = StringUtil.newUUID();
		String certNo = String.valueOf(StringUtil.randomInt(1000, 9999));

		// 입력
		request.setCertKey(certKey);
		request.setCertNo(certNo);
		memberInfoService.insert_memberMobileCert(request);

		// sms 보내기
		try {

			File desti = new File("/home/tae11111/UPLOADS");
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

			}
			response.put("certKey", certKey);
			response.put("message", "인증번호를 발송 하였습니다.");

		} catch (Exception e) {
			response.put("message", "문자전송 실패");
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/certNo_confirm")
	public ResponseEntity<?> confirmCertNo(@RequestBody MemberInfoModel request) {
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
		return ResponseEntity.ok(response);
	}

	// 나머지 메서드 (memberSession, memberInfo, upload, update, logout)는 그대로 유지
	@GetMapping("/memberSession")
	public ResponseEntity<?> memberSession(@RequestHeader("Authorization") String bearerToken) {
		Map<String, Object> response = new HashMap<>();

		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token != null && jwtTokenProvider.validateToken(token)) {
			String memberId = jwtTokenProvider.getUsername(token);
			System.out.println("MemberSession - Token Validated, MemberId: " + memberId);

			MemberInfoModel model = new MemberInfoModel();
			model.setMemberId(memberId);
			MemberInfoModel rsModel = memberInfoService.getMap_memberInfo(model);

			response.put("memberId", rsModel.getMemberId());
			response.put("memberName", rsModel.getMemberName());
			response.put("memberType", rsModel.getMemberType());
			return ResponseEntity.ok(response);
		}

		System.out.println("MemberSession - Invalid or Missing Token");
		response.put("message", "유효하지 않은 토큰");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@GetMapping("/memberInfo")
	public ResponseEntity<?> memberInfo(@RequestHeader("Authorization") String bearerToken) {
		Map<String, Object> response = new HashMap<>();

		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token == null || !jwtTokenProvider.validateToken(token)) {
			System.out.println("MemberInfo - Invalid or Missing Token");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		String memberId = jwtTokenProvider.getUsername(token);
		MemberInfoModel model = new MemberInfoModel();
		model.setMemberId(memberId);
		MemberInfoModel rsModel = memberInfoService.getMap_memberInfo(model);

		response.put("member", rsModel);
		System.out.println("Member Info Fetched - MemberId: " + memberId);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String bearerToken) {
		Map<String, String> response = new HashMap<>();

		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token == null || !jwtTokenProvider.validateToken(token)) {
			response.put("message", "유효하지 않은 토큰");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		if (file.isEmpty()) {
			response.put("message", "파일이 없습니다.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		String UPLOAD_DIR = "/home/tae11111/UPLOADS";
		String imageUrlPath = "https://adm.xn--hy1b84gv7mikb.kr/uploads/";
		File fileDir = new File(UPLOAD_DIR);
		if (!fileDir.exists()) {
			UPLOAD_DIR = "C:/LOCAL_FILE";
			imageUrlPath = "http://localhost:8093/uploads/";
		}

		try {
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			String uuid = StringUtil.getUUID();

			String fileName = StringUtil.getUUID(); //System.currentTimeMillis() + "_" + file.getOriginalFilename();
			String filePath = Paths.get(UPLOAD_DIR, fileName).toString();
			file.transferTo(new File(filePath));

			String imageUrl = imageUrlPath + fileName;
			System.out.println("File Uploaded - URL: " + imageUrl);

			response.put("imageUrl", imageUrl);
			response.put("fileName", fileName);
			response.put("message", "파일 업로드 성공");
			return ResponseEntity.ok(response);
		} catch (IOException e) {
			System.out.println("File Upload Failed: " + e.getMessage());
			response.put("message", "파일 업로드 실패");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/member/update")
	public ResponseEntity<String> updateUserInfo(@RequestBody MemberInfoModel request, @RequestHeader("Authorization") String bearerToken) {
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token == null || !jwtTokenProvider.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰");
		}

		String memberId = jwtTokenProvider.getUsername(token);
		System.out.println("Update Request: " + request);
		request.setMemberId(memberId);

		if (request.getPassword() != null && !request.getPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(request.getPassword());
			request.setMemberPassword(encodedPassword);
			System.out.println("Password Updated - MemberId: " + memberId + ", Encoded: " + encodedPassword);
		}

		if (request.getJikjongNames() != null) {
			request.setJikjongName(String.join(",", request.getJikjongNames()));
		}

		memberInfoService.update_memberInfo(request);
		System.out.println("Member Updated - MemberId: " + memberId);
		return ResponseEntity.ok("회원정보 수정 성공");
	}

	@GetMapping("/logout")
	public ResponseEntity<Void> logout() {
		System.out.println("Logout - No server-side action required for JWT");
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/find_id")
	public ResponseEntity<?> find_id(@RequestBody MemberInfoModel request) {
		
		
		Map<String, Object> response = new HashMap<>();
		System.out.println("Register Request: " + request);

		try {
			// 인증 확인
			MemberInfoModel rsModel = memberInfoService.getMap_memberMobileCert(request);
			if (rsModel == null || !StringUtil.NVL(rsModel.getMobileNo()).equals(request.getMobileNo())) {
				response.put("message", "인증확인 정보가 변경되었습니다. 다시 인증해 주세요.");
				return ResponseEntity.ok(response);
			}
			
			request.setSelectMode("findId");
			MemberInfoModel memberInfo = memberInfoService.getMap_memberInfo(request);
			
			String findMemberId = memberInfo.getMemberId();
			
			if (memberInfo == null ) {
				response.put("message", "등록된 회원이 없습니다.");
				return ResponseEntity.ok(response);
			}

			File desti = new File("/home/tae11111/UPLOADS");
			if (desti.exists()) {

				System.out.println(">>>>>>>>");

				// SMS전송
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("smsTelNo", request.getMobileNo());
				smsMap.put("smsMsgText", "일드리오 회원 아이디 [" + findMemberId + "] 입니다.");
				smsMap.put("smsSubject", "회원 아이디");
				String smsResult = SendMmsMessage.sms_ppurio(smsMap);

				if (smsResult.indexOf("ok") > -1) {
					response.put("message", "회원아이디 전송");
				} else {
					response.put("message", smsResult);
				}
			} else {
				System.out.println("LOCAL " + findMemberId);

			}
			response.put("message", "OK");

		} catch (Exception e) {
			response.put("message", "문자전송 실패");
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/find_password")
	public ResponseEntity<?> find_password(@RequestBody MemberInfoModel request) {
		
		
		Map<String, Object> response = new HashMap<>();
		System.out.println("getMemberId " + request.getMemberId());
	
		try {
			// 인증 확인
			MemberInfoModel rsModel = memberInfoService.getMap_memberMobileCert(request);
			if (rsModel == null || !StringUtil.NVL(rsModel.getMobileNo()).equals(request.getMobileNo())) {
				response.put("message", "인증확인 정보가 변경되었습니다. 다시 인증해 주세요.");
				return ResponseEntity.ok(response);
			}
			
			request.setSelectMode("findId");
			MemberInfoModel memberInfo = memberInfoService.getMap_memberInfo(request);
			
			String findMemberId = memberInfo.getMemberId();
			String memberPasswordDec = memberInfo.getMemberPasswordDec();
			System.out.println("memberPasswordDec >>>>> " +memberPasswordDec);
			
			if (memberInfo == null ) {
				response.put("message", "등록된 회원이 없습니다.");
				return ResponseEntity.ok(response);
			}else {
				
				System.out.println("Password Encoded - MemberId: " + request.getMemberId() + ", memberPasswordDec: " + memberPasswordDec);
			}
	
			File desti = new File("/home/tae11111/UPLOADS");
			if (desti.exists()) {
	
				System.out.println(">>>>>>>>");
	
				// SMS전송
				Map<String, String> smsMap = new HashMap<String, String>();
				smsMap.put("smsTelNo", request.getMobileNo());
				smsMap.put("smsMsgText", "일드리오 임시비밀번호 ["+ memberPasswordDec + "] 입니다.");
				smsMap.put("smsSubject", "회원 임시비밀번호");
				String smsResult = SendMmsMessage.sms_ppurio(smsMap);
	
				if (smsResult.indexOf("ok") > -1) {
					response.put("message", "임시 비밀번호를 핸드폰으로 발송 하였습니다.");
				} else {
					response.put("message", smsResult);
				}
			} else {
				System.out.println("회원 임시비밀번호 " + memberPasswordDec);
	
			}
			response.put("message", "OK");
	
		} catch (Exception e) {
			response.put("message", "문자전송 실패");
		}
		return ResponseEntity.ok(response);
	}

	
	
	@PostMapping("/member-password")
	public ResponseEntity<?> memberPassword(@RequestBody MemberInfoModel request, @RequestHeader("Authorization") String bearerToken) {
		
		Map<String, Object> response = new HashMap<>();
		
		String token = bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
		if (token == null || !jwtTokenProvider.validateToken(token)) {
			response.put("message", "유효하지 않은 토큰");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		String memberId = jwtTokenProvider.getUsername(token);
		System.out.println("Update Request: " + request);
		request.setMemberId(memberId);
		request.setMemberPasswordDec(request.getNewPassword());

		if (request.getNewPassword() != null && !request.getNewPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(request.getNewPassword());
			request.setMemberPassword(encodedPassword);
			request.setMemberPasswordDec(request.getNewPassword());
			System.out.println("Password Updated - MemberId: " + memberId + ", Encoded: " + encodedPassword);
		}


		request.setTrxnMode("update_password");
		memberInfoService.update_memberInfo(request);
		response.put("message", "OK");
		return ResponseEntity.ok(response);
	}
}