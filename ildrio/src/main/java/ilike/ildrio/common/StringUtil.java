package ilike.ildrio.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 개요 : 공통 문자열 처리 유틸 클래스
 */
public class StringUtil {
	/** 개요 : 생성자 */
	private StringUtil() {
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static String nullConvert(Object src) {
		// if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
		if (src != null && src instanceof java.math.BigDecimal) {
			return ((BigDecimal) src).toString();
		}

		if (src == null || src.equals("null")) {
			return "";
		} else {
			return ((String) src).trim();
		}
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * NULL값을 빈값으로 변경 (String형)
	 * 
	 * @param str
	 * @return
	 */
	public static String NVL(String str) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return "";
		else
			return str;
	}

	/**
	 * NULL값을 빈값이나 지정된 값으로 변경 (String형)
	 * 
	 * @param str
	 * @param nvlstr
	 * @return
	 */
	public static String NVLS(String str, String nvlstr) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return nvlstr;
		else
			return str;
	}

	/**
	 * NULL값을 0으로 변경 (Integer형)
	 * 
	 * @param str
	 * @return
	 */
	public static int NVLI(String str) {

		if (str == null || str.trim().equals("") || str.trim().equals("null")) {
			return 0;
		} else {
			str = str.replace(",", "");
			return Integer.parseInt(str);
		}
	}

	/**
	 * NULL값을 0으로 변경 (String형)
	 * 
	 * @param str
	 * @return
	 */
	public static String NVLZ(String str) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return "0";
		else
			return str;
	}

	/**
	 * NULL값을 빈값이나 지정된 값으로 변경 (Integer형)
	 * 
	 * @param str
	 * @param nvlint
	 * @return
	 */
	public static int NVLI(String str, String nvlint) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return Integer.parseInt(nvlint);
		else
			return Integer.parseInt(str);
	}

	public static String fmtDate(String str, String sdot) {

		if (str.isEmpty()) {
			str = "";
		} else {
			str = str.replace("-", "").replace(".", "").replace("/", "");
			if (str.length() >= 8) {
				str = str.substring(0, 4) + sdot + str.substring(4, 6) + sdot + str.substring(6, 8);
			} else {
				if (str.length() == 6) {
					str = str.substring(0, 4) + sdot + str.substring(4, 6);
				}
			}
		}
		return str;
	}

	public static String getTimeStamp() {

		java.util.Calendar calendar = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String strDateTime = dateFormat.format(calendar.getTime());

		return strDateTime.replace(":", "").replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
	}

	public static String getNow(String optn) {

		java.util.Calendar calendar = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String strDateTime = dateFormat.format(calendar.getTime());

		String rtn = "";
		if (optn.equals("dateTime")) {
			rtn = strDateTime.substring(0, 19);
		}
		if (optn.equals("timeStamp")) {
			rtn = strDateTime.replace(":", "").replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
		}
		if (optn.equals("yyyy-MM-dd")) {
			rtn = strDateTime.substring(0, 10);
		}
		if (optn.equals("year")) {
			rtn = strDateTime.substring(0, 4);
		}
		if (optn.equals("month")) {
			rtn = strDateTime.substring(5, 7);
		}
		if (optn.equals("day")) {
			rtn = strDateTime.substring(8, 10);
		}
		if (optn.equals("yyyymmdd")) {
			rtn = strDateTime.replace("-", "").substring(0, 8);
		}
		return rtn;
	}
	
	public static String getTimeZone() {
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

		return sdf.format(date);
	}

	public static String addMonth(String frDate, int months) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date DfrDate = sdf.parse(frDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(DfrDate);
		cal.add(Calendar.MONTH, months);

		String toDate = sdf.format(cal.getTime());

		return toDate;
	}

	public static int monthDiff(String frDate, String toDate) throws ParseException {

		frDate = frDate.replace("-", "").replace(".", "");
		toDate = toDate.replace("-", "").replace(".", "");

		int strtYear = Integer.parseInt(frDate.substring(0, 4));
		int strtMonth = Integer.parseInt(frDate.substring(4, 6));

		int endYear = Integer.parseInt(toDate.substring(0, 4));
		int endMonth = Integer.parseInt(toDate.substring(4, 6));

		int monthDiff = (endYear - strtYear) * 12 + (endMonth - strtMonth);

		return monthDiff;
	}

	public static long dateDiff(String frDate, String toDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		Date beginDate = formatter.parse(frDate);
		Date endDate = formatter.parse(toDate);

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;

	}

	public static String dateAdd(String stdDate, int addDays) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date date = df.parse(stdDate);

		// 날짜 더하기
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDays);

		return df.format(cal.getTime());

	}

	public static String addMinute(String startHHmm, int addMin) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");

		Date stdDate = sdf.parse("20001231 " + startHHmm);

		Calendar cal = Calendar.getInstance();
		cal.setTime(stdDate);
		cal.add(Calendar.MINUTE, addMin);

		String toDate = sdf.format(cal.getTime());

		// System.out.println(toDate);

		return toDate;
	}

	public static String getDateDiff(String strFrDate, int iDiffDays) {

		String rtn = "";

		java.util.Calendar calendar = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

		try {
			java.util.Date date = df.parse(strFrDate);

			// 날짜 더하기
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(date);
			cal.add(java.util.Calendar.DATE, iDiffDays);

			rtn = df.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtn;
	}

	public static int getNowTimeDiff(String strCompareDate) {

		int iRtn = 0;

		java.util.Calendar calendar = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strCurrentDateTime = dateFormat.format(calendar.getTime());

		java.util.Date currentDateTime = null;
		java.util.Date compareDateTime = null;

		try {

			if (strCompareDate.length() > 10) {

				currentDateTime = dateFormat.parse(strCurrentDateTime);
				compareDateTime = dateFormat.parse(strCompareDate);

				// in milliseconds
				long diff = currentDateTime.getTime() - compareDateTime.getTime();

				long diffSeconds = diff / 1000;
				long diffMinutes = diffSeconds / 60;
				long diffHours = diffMinutes / 60;

				long diffDays = diff / (24 * 60 * 60 * 1000);

				iRtn = (int) diffMinutes;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return iRtn;
	}

	public static String newUUID() {

		return UUID.randomUUID().toString();
	}

	public static int randomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static String getListPageNavigation(int iDataCnt, int iPageRow, int iCurrPage, int iPageBlock) {

		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);

		int iBlockRemainder = iCurrPage % iPageBlock;
		if (iBlockRemainder > 0) {
			iCurrBlock = iCurrBlock + 1;
		}
		iDataCnt = iDataCnt + (iCurrBlock - 1) * iPageRow * iPageBlock;

		int iPageCnt = (int) Math.floor((double) iDataCnt / (double) iPageRow);
		int iRemainder = iDataCnt % iPageRow;
		if (iRemainder > 0) {
			iPageCnt = iPageCnt + 1;
		}

		int iFrPage = (iCurrBlock - 1) * iPageBlock + 1;
		int iToPage = iCurrBlock * iPageBlock;
		if (iToPage > iPageCnt) {
			iToPage = iPageCnt;
		}

		if (iPageCnt < 1) {
			iPageCnt = 1;
		}

		String nextPageYn = "N";
		if (iPageCnt > iCurrPage) {
			nextPageYn = "Y";
		}

		System.out.println("iDataCnt:" + iDataCnt + " /iPageRow : " + iPageRow + " /iCurrPage : " + iCurrPage + " /iCurrBlock : " + iCurrBlock + " / iPageBlock: " + iPageBlock + " / iFrPage: " + iFrPage + " / iToPage: " + iToPage);

		String str = "<ul class=^pagination mt-3 justify-content-center pagination_style1^>     ";

		if (iFrPage > 1) {
			str += "	<li class=^page-item^>  ";
			str += "		<a class=^page-link^ onclick=^fn_searchPage('" + (iFrPage - 1) + "')^>";
			str += "			<i class=^linearicons-arrow-left^></i> ";
			str += "		</a> ";
			str += "	</li> ";
		}

		for (int i = iFrPage; i <= iToPage; i++) {

			if (i == iCurrPage) {
				str += "	<li class=^page-item active^> ";
				str += "		<a class=^page-link^ onclick=^fn_searchPage('" + i + "')^>" + i + "</a> ";
				str += "	</li>  ";
			} else {
				str += "	<li class=^page-item ^> ";
				str += "		<a class=^page-link^ onclick=^fn_searchPage('" + i + "')^>" + i + "</a> ";
				str += "	</li>  ";
			}
		}

		if (iToPage < iPageCnt) {
			str += "	<li class=^page-item^>  ";
			str += "		<a class=^page-link^ onclick=^fn_searchPage('" + (iToPage + 1) + "')^>  ";
			str += "			<i class=^linearicons-arrow-right^></i>  ";
			str += "		</a> ";
			str += "	</li> ";
		}
		str += "</ul>";

		return str.replace("^", "\"");
	}

	public static String getGridPageNavigation(int iDataCnt, int iPageRow, int iCurrPage, int iPageBlock) {

		int iCurrBlock = (int) Math.floor((double) iCurrPage / (double) iPageBlock);

		int iBlockRemainder = iCurrPage % iPageBlock;
		if (iBlockRemainder > 0) {
			iCurrBlock = iCurrBlock + 1;
		}
		iDataCnt = iDataCnt + (iCurrBlock - 1) * iPageRow * iPageBlock;

		int iPageCnt = (int) Math.floor((double) iDataCnt / (double) iPageRow);
		int iRemainder = iDataCnt % iPageRow;
		if (iRemainder > 0) {
			iPageCnt = iPageCnt + 1;
		}

		int iFrPage = (iCurrBlock - 1) * iPageBlock + 1;
		int iToPage = iCurrBlock * iPageBlock;
		if (iToPage > iPageCnt) {
			iToPage = iPageCnt;
		}

		if (iPageCnt < 1) {
			iPageCnt = 1;
		}

		String nextPageYn = "N";
		if (iPageCnt > iCurrPage) {
			nextPageYn = "Y";
		}

		System.out.println("iDataCnt:" + iDataCnt + " /iPageRow : " + iPageRow + " /iCurrPage : " + iCurrPage + " /iCurrBlock : " + iCurrBlock + " / iPageBlock: " + iPageBlock + " / iFrPage: " + iFrPage + " / iToPage: " + iToPage);

		String str = "";

		if (iFrPage > 1) {
			str += "	<li class=^page-item^>  ";
			str += "		<a class=^page-link^ onclick=^fn_searchPage('" + (iFrPage - 1) + "')^>";
			str += "			<i class=^linearicons-chevron-left^></i> ";
			str += "		</a> ";
			str += "	</li> ";
		}

		for (int i = iFrPage; i <= iToPage; i++) {

			if (i == iCurrPage) {
				str += "	<li class=^page-item active^> ";
				str += "		<a class=^page-link^ onclick=^fn_searchPage('" + i + "')^>" + i + "</a> ";
				str += "	</li>  ";
			} else {
				str += "	<li class=^page-item ^> ";
				str += "		<a class=^page-link^ onclick=^fn_searchPage('" + i + "')^>" + i + "</a> ";
				str += "	</li>  ";
			}
		}

		if (iToPage < iPageCnt) {
			str += "	<li class=^page-item^>  ";
			str += "		<a class=^page-link^ onclick=^fn_searchPage('" + (iToPage + 1) + "')^>  ";
			str += "			<i class=^linearicons-chevron-right^></i> ";
			str += "		</a> ";
			str += "	</li> ";
		}

		return str.replace("^", "\"");
	}

	public static String getFulltextSearch(String searchText) {
		String[] arr_searchText = searchText.split(" ");
		String srh_searchText = "";
		if(arr_searchText.length > 1) {
			for(int i = 0; i < arr_searchText.length; i++) {
				if(!arr_searchText[i].equals("")) {
					srh_searchText += "+\""+arr_searchText[i]+"\" ";
				}
			}
		}else {
			srh_searchText = searchText; 
		}
		
		return srh_searchText;
		
	}
	
	public static String getLikeSearch(String columnName,String searchText) {
		String[] arr_searchText = searchText.split(" ");
		String srh_searchText = "";
		
		if(!searchText.equals("")) {
			if(arr_searchText.length > 1) {
				for(int i = 0; i < arr_searchText.length; i++) {
					if(!arr_searchText[i].equals("")) {
						srh_searchText += " and "+columnName+ " like '%"+arr_searchText[i]+"%' ";
					}
				}
			}else {
				srh_searchText = " and "+columnName+ " like '%"+searchText+"%' "; 
			}
		}
		
		return srh_searchText;
		
	}
	
	/**
	 * getUUID
	 * @return
	 */
	public static String getUUID() {
		
        return UUID.randomUUID().toString();
    }
}