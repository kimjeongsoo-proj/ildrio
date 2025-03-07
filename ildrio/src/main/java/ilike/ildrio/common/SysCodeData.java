package ilike.ildrio.common; 
 
public class SysCodeData { 
	 
	public static String getSysCodeData(String codeId) { 
 
		String src = ""; 
		if (codeId.equals("agency_status_code")) { 
			src += "♥자유^자유"; 
			src += "♥전속^전속"; 
		} 
 
		if (codeId.equals("career_code")) { 
			src += "♥초보^초보"; 
			src += "♥보조^보조"; 
			src += "♥준공^준공"; 
			src += "♥기공^기공"; 
		} 
 
		if (codeId.equals("gender_type")) { 
			src += "♥납^남"; 
			src += "♥여^여"; 
		} 
 
		if (codeId.equals("job_gender_type")) { 
			src += "♥남^남"; 
			src += "♥여^여"; 
			src += "♥구분없음^구분없음"; 
		} 
 
		if (codeId.equals("job_status_code")) { 
			src += "♥임시^임시"; 
			src += "♥요청접수^요청접수"; 
			src += "♥지원중^지원중"; 
			src += "♥지원마감^지원마감"; 
			src += "♥채용중^채용중"; 
			src += "♥채용완료^채용완료"; 
			src += "♥취소^취소"; 
		} 
 
		if (codeId.equals("posting_status")) { 
			src += "♥임시^임시"; 
			src += "♥공고^공고"; 
			src += "♥숨김^숨김"; 
		} 
 
		if (codeId.equals("ROOT")) { 
			src += "♥tech_code^기술코드"; 
			src += "♥career_code^경력코드"; 
			src += "♥gender_type^성별"; 
			src += "♥YN^여부"; 
			src += "♥job_gender_type^채용성별구분"; 
			src += "♥work_type^근무형태"; 
			src += "♥work_term_type^근무기간유형"; 
			src += "♥agency_status_code^소속사상태코드"; 
			src += "♥job_status_code^채용상태코드"; 
			src += "♥posting_status^공고상태"; 
		} 
 
		if (codeId.equals("tech_code")) { 
			src += "♥전체^전체"; 
			src += "♥내장목공^내장목공"; 
			src += "♥외장목공^외장목공"; 
			src += "♥전기^전기"; 
			src += "♥통신^통신"; 
			src += "♥소방^소방"; 
			src += "♥조경^조경"; 
			src += "♥창호^창호"; 
			src += "♥수장^수장"; 
		} 
 
		if (codeId.equals("work_term_type")) { 
			src += "♥일근무^일근무"; 
			src += "♥주근무^주근무"; 
			src += "♥월근무^월근무"; 
			src += "♥특정기간^특정기간"; 
		} 
 
		if (codeId.equals("work_type")) { 
			src += "♥프로^프로"; 
			src += "♥장기근무^장기근무"; 
			src += "♥단기근무^단기근무"; 
			src += "♥아르바이트^아르바이트"; 
		} 
 
		if (codeId.equals("YN")) { 
			src += "♥Y^Yes"; 
			src += "♥N^No"; 
    }

		if (src.equals("")) {
			src  += "♥1^1"; 
			src  += "♥2^2"; 
			src  += "♥3^3"; 
			src  += "♥4^4"; 
			src  += "♥5^5"; 
			src  += "♥6^6"; 
			src  += "♥7^7"; 
			src  += "♥8^8"; 
			src  += "♥9^9"; 
			src  += "♥10^10"; 
		}
	 
		return src; 
	} 
	 
} 