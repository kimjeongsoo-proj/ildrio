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

public class SysCodeDataGen {

	public static void main(String[] args) throws Exception {

		proc_SysCodeData();

	}

	public static void proc_SysCodeData() {

		String src = "";
		src += "package ilike.ildrio.common; ";
		src += "\n ";
		src += "\npublic class SysCodeData { ";
		src += "\n	 ";
		src += "\n	public static String getSysCodeData(String codeId) { ";
		src += "\n ";
		src += "\n		String src = \"\"; ";
		

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
						src += "\n		} ";
						src += "\n ";
						plus = " =";
					}
					src += "\n		if (codeId.equals(\"" + code_id + "\")) { ";

					chk_code_id = code_id;
				}

				src += "\n			src += \"♥" + code_value.trim() + "^" + code_text.trim() + "\"; ";
				plus = "+=";
			}

			src += "\n    }";
			src += "\n";
			src += "\n		if (src.equals(\"\")) {";
			for (int i = 1; i <= 10; i++) {
				src += "\n			src  += \"♥" + i + "^" + i + "\"; ";
			}
			src += "\n		}";
			src += "\n	 ";
			src += "\n		return src; ";
			src += "\n	} ";
			src += "\n	 ";
			src += "\n} ";

			System.out.println(src);

			String filePath = "C:/eGovFrame3.9/workspace/ildrio/src/main/java/ilike/ildrio/common";
			String fileNm = "SysCodeData.java";
			srcFileWrite(filePath, fileNm, src);

		} catch (Exception e) {
			e.printStackTrace();
		}

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
					System.out.println("FILE :: " + filePath + "/" + fileNm);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}