package ilike.ildrio.controller.sys;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import ilike.ildrio.common.RequestUtil;
import ilike.ildrio.common.RequiredSession;
import ilike.ildrio.common.SessionUtil;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.common.DBCPTransaction;

public class SysCodeFunction {

	public static void main(String[] args) throws Exception {

		proc_SysCodeFunction();

	}
	public static String proc_SysCodeFunction() {

		String rtn = "";
		rtn += "\n  function cfn_CmmCdSrc(codeId) { ";
		rtn += "\n     var src = \"\"; ";

		try {

			String sql = "";
			sql += "\n select code_id, code_value, code_text ";
			sql += "\n from sys_code ";
			sql += "\n where ifnull(code_id,'') != '' ";
			sql += "\n order by code_id, sort_order  ";

			ArrayList<HashMap<String, String>> rsList = DBCPTransaction.dbcpSelectList(sql);

			String chk_code_id = "";
			String plus = " =";
			for (int i = 0; i < rsList.size(); i++) {

				HashMap<String, String> rsMap = rsList.get(i);

				String code_id = StringUtil.NVL(rsMap.get("code_id"));
				String code_value = StringUtil.NVL(rsMap.get("code_value"));
				String code_text = StringUtil.NVL(rsMap.get("code_text"));

				if (!chk_code_id.equals(code_id)) {

					if (!chk_code_id.equals("")) {
						rtn += "\n    } ";
						rtn += "\n";
						plus = " =";
					}
					rtn += "\n    if (codeId == \"" + code_id + "\") {";

					chk_code_id = code_id;
				}

				rtn += "\n      src " + plus + " \"♥" + code_value.trim() + "^" + code_text.trim() + "\"; ";
				plus = "+=";
			}

			rtn += "\n    }";
			rtn += "\n";
			rtn += "\n    if (src == \"\") {";
			for (int i = 1; i <= 10; i++) {
				rtn += "\n      src  += \"♥"+i+"^"+i+"\"; ";
			}
			rtn += "\n    }";

			rtn += "\n";
			rtn += "\n  return src;                                                                                                        ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n}                                                                                                                    ";
			rtn += "\n                                                                                                                     ";
			rtn += "\nfunction cfn_CmmCd(codeId, opt, currCode) {                                                                          ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n  var src = cfn_CmmCdSrc(codeId);                                                                                    ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n  var arrSrc = src.split(\"♥\");                                                                                    ";
			rtn += "\n  var rtn = \"\";                                                                                                    ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n  if (opt == \"select\") {                                                                                           ";
			rtn += "\n    rtn = \"<option value=\'\'></option>\";                                                                                                    ";
			rtn += "\n    for (i = 1; i < arrSrc.length; i++) {                                                                            ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n      var arrOption = arrSrc[i].split(\"^\");                                                                        ";
			rtn += "\n      if (arrOption[0] == currCode) {                                                                                ";
			rtn += "\n        rtn += \"<option value=\'\" + arrOption[0] + \"\' selected>\" + arrOption[1] + \"</option>\";                ";
			rtn += "\n      } else {                                                                                                       ";
			rtn += "\n        rtn += \"<option value=\'\" + arrOption[0] + \"\'>\" + arrOption[1] + \"</option>\";                         ";
			rtn += "\n      }                                                                                                              ";
			rtn += "\n    }                                                                                                                ";
			rtn += "\n  }                                                                                                                  ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n  if (opt == \"value\") {                                                                                            ";
			rtn += "\n    rtn = currCode;                                                                                                    ";
			rtn += "\n    for (i = 1; i < arrSrc.length; i++) {                                                                            ";
			rtn += "\n      var arrOption = arrSrc[i].split(\"^\");                                                                        ";
			rtn += "\n      if (arrOption[0] == currCode) {                                                                                ";
			rtn += "\n        rtn = arrOption[1];                                                                                          ";
			rtn += "\n      }                                                                                                              ";
			rtn += "\n    }                                                                                                                ";
			rtn += "\n  }                                                                                                                  ";
			rtn += "\n                                                                                                                     ";
			rtn += "\n  return rtn;                                                                                                        ";
			rtn += "\n}                                                                                                                    ";

			System.out.println(rtn);

			String filePath = "C:/eGovFrame3.9/workspace/ildrio/src/main/resources/static/resources/custom-js";
			String fileNm = "common_sysCode.js";
			srcFileWrite(filePath, fileNm, rtn);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtn;
	}

	public static void srcFileWrite(String filePath, String fileNm, String src) throws Exception {

		try {

			if (1 == 1) {

				BufferedWriter output = null;
				File fdir = new File(filePath);

				if (false == fdir.exists()) {
					fdir.mkdirs();
				}

				File file = new File(filePath + "/" + fileNm);

				if (false == file.exists()) {

					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF8"));
					writer.write(src);
					writer.close();

					System.out.println("FILE :: " + filePath + "/" + fileNm);
				} else {
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF8"));
					writer.write(src);
					writer.close();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}