package ilike.ildrio.common.ppurio;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import ilike.ildrio.common.DBCPTransaction;
import ilike.ildrio.common.StringUtil;

public class SendMmsMessage {

	/*
	 * 뿌리오 발송 API 경로 - 서버측 인코딩과 응답형태에 따라 선택
	 */
	final String SEND_API_URL = "https://message.ppurio.com/api/send_euckr_text.php"; // EUC-KR 인코딩과 TEXT 응답용 호출 페이지
	// final String SEND_API_URL =
	// "https://message.ppurio.com/api/send_euckr_json.php"; // EUC-KR 인코딩과 JSON 응답용
	// 호출 페이지
	// final String SEND_API_URL =
	// "https://message.ppurio.com/api/send_euckr_xml.php"; // EUC-KR 인코딩과 XML 응답용
	// 호출 페이지
	// final String SEND_API_URL =
	// "https://message.ppurio.com/api/send_utf8_text.php"; // UTF-8 인코딩과 TEXT 응답용
	// 호출 페이지
	// final String SEND_API_URL =
	// "https://message.ppurio.com/api/send_utf8_json.php"; // UTF-8 인코딩과 JSON 응답용
	// 호출 페이지
	// final String SEND_API_URL =
	// "https://message.ppurio.com/api/send_utf8_xml.php"; // UTF-8 인코딩과 XML 응답용 호출
	// 페이지

	/*
	 * 뿌리오 예약취소 API 경로 - 서버측 인코딩과 응답형태에 따라 선택
	 */
	String CANCEL_API_URL = "https://message.ppurio.com/api/cancel_euckr_text.php"; // EUC-KR 인코딩과 TEXT 응답용 호출 페이지
	// String CANCEL_API_URL =
	// "https://message.ppurio.com/api/cancel_euckr_json.php"; // EUC-KR 인코딩과 JSON
	// 응답용 호출 페이지
	// String CANCEL_API_URL =
	// "https://message.ppurio.com/api/cancel_euckr_xml.php"; // EUC-KR 인코딩과 XML 응답용
	// 호출 페이지
	// String CANCEL_API_URL =
	// "https://message.ppurio.com/api/cancel_utf8_text.php"; // UTF-8 인코딩과 TEXT 응답용
	// 호출 페이지
	// String CANCEL_API_URL =
	// "https://message.ppurio.com/api/cancel_utf8_json.php"; // UTF-8 인코딩과 JSON 응답용
	// 호출 페이지
	// String CANCEL_API_URL = "https://message.ppurio.com/api/cancel_utf8_xml.php";
	// // UTF-8 인코딩과 XML 응답용 호출 페이지

	// application이 사용하는 character set에 따라 변경 ex> EUC-KR, UTF-8
	private String charset = "EUC-KR";
	private final String boundary;
	private static final String LINE_FEED = "\r\n";

	public static void main(String[] args) throws Exception {

		String userid = "jescom"; // [필수] 뿌리오 아이디
		String callback = "027935335"; // [필수] 발신번호 - 숫자만
		String phone = "01024967783"; // [필수] 수신번호 - 여러명일 경우 |로 구분 "010********|010********|010********"
		String msg = "테스트 발송입니다"; // [필수] 문자내용 - 이름(names)값이 있다면 [*이름*]가 치환되서 발송됨
		String names = ""; // [선택] 이름 - 여러명일 경우 |로 구분 "홍길동|이순신|김철수"
		String appdate = ""; // [선택] 예약발송 (현재시간 기준 10분이후 예약가능)
		String subject = "테스트"; // [선택] 제목 (30byte)
		String file1Path = ""; // [선택] 포토발송 (jpg, jpeg만 지원 300 K 이하)

		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("smsTelNo","01024967783");
		paramMap.put("smsMsgText","테스트 발송입니다");
		paramMap.put("smsSubject","테스트");
		
		try {


			sms_ppurio(paramMap);
			
			//SendMmsMessage sendMmsMessage = new SendMmsMessage();

			// filePath가 null 혹은 blank("")인 경우 일반 단/장문 발송.
			//String response_str = sendMmsMessage.send(userid, callback, phone, msg, names, appdate, subject, file1Path);

			// response
			//System.out.println("=============================");
			//System.out.println(response_str);
			//System.out.println("=============================");

		} catch (IOException localIOException) {
			//System.out.println(localIOException.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*
		 * 응답값
		 *
		 *  <성공시>
		 *    "ok|sms|123456789|1"           - 전송결과|발송형태(단문은 sms 장문은 lms 포토문자는 mms)|발송 msgid (예약취소시 필요)|발송건수
		 *
		 *  <실패시>
		 *    "invalid_member"               - 연동서비스 신청이 안 됐거나 없는 아이디
		 *    "under_maintenance"            - 요청시간에 서버점검중인 경우
		 *    "allow_https_only"             - http 요청인 경우
		 *    "invalid_ip"                   - 등록된 접속가능 IP가 아닌 경우
		 *    "invalid_msg"                  - 문자내용에 오류가 있는 경우
		 *    "invalid_names"                - 이름에 오류가 있는 경우
		 *    "invalid_subject"              - 제목에 오류가 있는 경우
		 *    "invalid_sendtime"             - 예약발송 시간에 오류가 있는 경우 (10분이후부터 다음해말까지 가능)
		 *    "invalid_sendtime_maintenance" - 예약발송 시간에 서버점검 예정인 경우
		 *    "invalid_phone"                - 수신번호에 오류가 있는 경우
		 *    "invalid_msg_over_max"         - 문자내용이 너무 긴 경우
		 *    "invalid_callback"             - 발신번호에 오류가 있는 경우
		 *    "once_limit_over"              - 1회 최대 발송건수 초과한 경우
		 *    "daily_limit_over"             - 1일 최대 발송건수 초과한 경우
		 *    "not_enough_point"             - 잔액이 부족한 경우
		 *    "over_use_limit"               - 한달 사용금액을 초과한 경우
		 *    "server_error"                 - 기타 서버 오류
		 */

	}

	public static String sms_ppurio(Map<String, String> paramMap ) throws Exception {
		
		String response_str = "";
		
		String userid = "jescom"; // [필수] 뿌리오 아이디
		String callback = "027935335"; // [필수] 발신번호 - 숫자만
		String phone =  paramMap.get("smsTelNo").replace("-", ""); /// [필수] 수신번호 - 여러명일 경우 |로 구분 "010********|010********|010********"
		String msg =  paramMap.get("smsMsgText"); // [필수] 문자내용 - 이름(names)값이 있다면 [*이름*]가 치환되서 발송됨
		String names = ""; // [선택] 이름 - 여러명일 경우 |로 구분 "홍길동|이순신|김철수"
		String appdate = ""; // [선택] 예약발송 (현재시간 기준 10분이후 예약가능)
		String subject =  paramMap.get("smsSubject"); // [선택] 제목 (30byte)
		String file1Path = ""; // [선택] 포토발송 (jpg, jpeg만 지원 300 K 이하)

		try {

			SendMmsMessage sendMmsMessage = new SendMmsMessage();

			// filePath가 null 혹은 blank("")인 경우 일반 단/장문 발송.
			response_str = sendMmsMessage.send(userid, callback, phone, msg, names, appdate, subject, file1Path);

			// response
			//System.out.println("=============================");
			System.out.println("SendMmsMessage >>>>>> "+response_str);
			//System.out.println("=============================");
			
			/**

			String sqlStr = "insert into mall_sms ( ordDocNo,smsTelNo ,smsRcvrNm ,smsMsgLen,smsSubject ,smsMsgText ,smsResult, regDttm ) ";
			sqlStr += " values ( ";
			sqlStr += "   '" + StringUtil.NVL(paramMap.get("ordDocNo")) + "' ";
			sqlStr += " , '" + StringUtil.NVL(paramMap.get("smsTelNo")) + "' ";
			sqlStr += " , '" + StringUtil.NVL(paramMap.get("smsRcvrNm")) + "' ";
			sqlStr += " , '" + StringUtil.NVL(paramMap.get("smsMsgLen")) + "' ";
			sqlStr += " , '" + StringUtil.NVL(paramMap.get("smsSubject")) + "' ";
			sqlStr += " , '" + StringUtil.NVL(paramMap.get("smsMsgText")) + "' ";
			sqlStr += " , '" + response_str + "' ";
			sqlStr += " , now() ";
			sqlStr += " ) ";
			

			DBCPTransaction.dbcbTrxnOne(sqlStr);
**/
		} catch (IOException localIOException) {
			//System.out.println(localIOException.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return response_str;
	}
	public SendMmsMessage() throws IOException {
		boundary = "===" + System.currentTimeMillis() + "===";
	}

	public String cancel(String userid, String msgid) throws Exception {
		HttpURLConnection httpConn = initHttpConnection(CANCEL_API_URL);
		OutputStream outputStream = httpConn.getOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

		addParameter(writer, "userid", userid);
		addParameter(writer, "msgid", msgid);
		sendFinish(writer);

		return closeHttpConnection(httpConn);
	}

	public String send(String userid, String callback, String phone, String msg, String names, String appdate, String subject, String filePath) throws Exception {
		HttpURLConnection httpConn = initHttpConnection(SEND_API_URL);
		OutputStream outputStream = httpConn.getOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
		File file1 = null;

		// filePath가 있는 경우 포토 발송
		if (filePath != null && !"".equals(filePath.trim()))
			file1 = new File(filePath);

		addParameter(writer, "userid", userid);
		addParameter(writer, "callback", callback);
		addParameter(writer, "phone", phone);
		addParameter(writer, "msg", msg);
		addParameter(writer, "names", names);
		addParameter(writer, "appdate", appdate);
		addParameter(writer, "subject", subject);

		if (file1 != null)
			addFile(writer, outputStream, "file1", file1);

		sendFinish(writer);

		return closeHttpConnection(httpConn);
	}

	private HttpURLConnection initHttpConnection(String requestUrl) throws IOException {
		URL url = new URL(requestUrl);
		HttpURLConnection httpConn;

		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		httpConn.setRequestProperty("User-Agent", "CodeJava Agent");

		return httpConn;
	}

	private void addParameter(PrintWriter writer, String name, String value) {
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
		writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.append(value).append(LINE_FEED);
		writer.flush();
	}

	private void addFile(PrintWriter writer, OutputStream outputStream, String name, File uploadFile) throws IOException {
		String fileName = uploadFile.getName();
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
		writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.flush();

		FileInputStream inputStream = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.flush();
		inputStream.close();
		writer.append(LINE_FEED);
		writer.flush();
	}

	private void sendFinish(PrintWriter writer) throws IOException {
		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();
	}

	private String closeHttpConnection(HttpURLConnection httpConn) throws IOException {
		StringBuffer response = new StringBuffer();

		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}
		return response.toString();
	}
}