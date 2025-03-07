package ilike.ildrio.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileUtil {
	
	
	public static void viewImg(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
		FileInputStream is = null;
		ServletOutputStream out = null;
		try {
			File file = new File(filePath);

			if (file.isFile()) {
				is = new FileInputStream(file);
				int bytes = (int) file.length();

				response.setContentType("images/unknown");
				response.setContentLength(bytes);

				response.setHeader("Content-Transfer-Encoding", "binary");

				out = response.getOutputStream();
				byte[] buffer = new byte[8192];
				while (bytes > 0) {
					bytes = is.read(buffer);
					if (bytes > 0) {
						out.write(buffer, 0, bytes);
						out.flush();
					}
				}

				is.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static void fileDownload(HttpServletRequest request, HttpServletResponse response, String dnFilePath, String dnFileName, String dnAsFileName) throws Exception {


		request.setCharacterEncoding("UTF-8");

		
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";

		try {
			try {
				file = new File(dnFilePath + dnFileName);
				in = new FileInputStream(file);
			} catch (FileNotFoundException fe) {
				skip = true;
			}
			client = request.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");

			if (!skip) {

				// IE
				if (client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition", "attachment; filename=" + new String(dnAsFileName.getBytes("KSC5601"), "ISO8859_1"));

				} else {
					// 한글 파일명 처리
					dnAsFileName = new String(dnAsFileName.getBytes("utf-8"), "iso-8859-1");

					response.setHeader("Content-Disposition", "attachment; filename=\"" + dnAsFileName + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				}

				response.setHeader("Content-Length", "" + file.length());

				
				os = response.getOutputStream();
				byte b[] = new byte[(int) file.length()];
				int leng = 0;

				while ((leng = in.read(b)) > 0) {
					os.write(b, 0, leng);
				}

			} else {
				response.setContentType("text/html;charset=UTF-8");
			}

			in.close();
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
