package ilike.ildrio.common;

import org.springframework.stereotype.Component;
import ilike.ildrio.common.SysCodeData;

@Component
public class SysCodeUtil {

	

	public String getSysCodeValue(String codeId, String option, String currCode) {
		String rtn = "";
		
		currCode = StringUtil.NVL(currCode);
		String src = SysCodeData.getSysCodeData(codeId);
		
		//System.out.println(src);

		String[] arrSrc = src.split("♥");
		
		
		if (option.equals("select")) {
			//rtn = "<option value=''></option>";
			for (int i = 1; i < arrSrc.length; i++) {

				String[] arrOption = arrSrc[i].split("\\^");
				if (arrOption[0].toUpperCase().equals(currCode.toUpperCase())) {
					rtn += "<option value='" + arrOption[0] + "' selected>" + arrOption[1] + "</option>";
				} else {
					rtn += "<option value='" + arrOption[0] + "'>" + arrOption[1] + "</option>";
				}
			}
		}
		
		if (option.equals("value")) {
			rtn = currCode;
			for (int i = 1; i < arrSrc.length; i++) {
				String[] arrOption = arrSrc[i].split("\\^");
				if (arrOption[0].toUpperCase().equals(currCode.toUpperCase())) {
					rtn = arrOption[1];
				}
			}
		}
		return rtn;
	}
	
	public String getSrhCodeValue(String codeId, String option, String currCode) {
		String rtn = "";
		
		currCode = StringUtil.NVL(currCode);
		String src = SysCodeData.getSysCodeData(codeId);
		
		//System.out.println(src);

		String[] arrSrc = src.split("♥");
		
		
		if (option.equals("select")) {
			rtn = "<option value=''></option>";
			for (int i = 1; i < arrSrc.length; i++) {

				String[] arrOption = arrSrc[i].split("\\^");
				if (arrOption[0].toUpperCase().equals(currCode.toUpperCase())) {
					rtn += "<option value='" + arrOption[0] + "' selected>" + arrOption[1] + "</option>";
				} else {
					rtn += "<option value='" + arrOption[0] + "'>" + arrOption[1] + "</option>";
				}
			}
		}
		
		if (option.equals("value")) {
			rtn = currCode;
			for (int i = 1; i < arrSrc.length; i++) {
				String[] arrOption = arrSrc[i].split("\\^");
				if (arrOption[0].toUpperCase().equals(currCode.toUpperCase())) {
					rtn = arrOption[1];
				}
			}
		}
		return rtn;
	}
	
	public String getSysCodeData(String codeId) {
		return SysCodeData.getSysCodeData(codeId);
	}
}