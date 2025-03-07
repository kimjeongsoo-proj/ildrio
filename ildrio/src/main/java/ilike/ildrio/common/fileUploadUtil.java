package ilike.ildrio.common;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.springframework.web.multipart.MultipartFile;

public class fileUploadUtil {

	public static void main(String[] args) throws Exception {
		String saverDirPath = "C:\\Temp\\uploads\\item\\18712917-7e35-4df7-b0ef-cb0f80e13609\\";
		String fileName = "Image 1.jpg";

		thumbImage(saverDirPath, fileName, "jpg", 300, 300);
	}

	/**
	 * 이미지파일 업로드
	 * 
	 * @param request
	 * @param uploadFile
	 * @param itemId
	 * @return
	 */

	public static Map<String, String> mediaUpload(HttpServletRequest request, MultipartFile[] uploadFile, String mediaAttachId) {

		String requestUrl = request.getRequestURL().toString();
		String uploadPathName = "/uploads/media/"+mediaAttachId+"/";
		String saverDirPath = "/app"+ uploadPathName;
		

		if (requestUrl.indexOf("localhost") > -1) {
			saverDirPath = "C:\\Temp\\uploads\\media\\"+mediaAttachId+"\\";
		}

		Map<String, String> rtnMap = new HashMap<String, String>();

		try {

			File fileDir = new File(saverDirPath);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}

			String fileName = "";
			Map<String, Object> result = new HashMap<>();
			for (MultipartFile multipartFile : uploadFile) {

				String originalFileName = multipartFile.getOriginalFilename();
				String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

				String saveFileName = originalFileName;

				File saveFile = new File(saverDirPath, saveFileName);
				try {
					multipartFile.transferTo(saveFile);
					rtnMap.put("filePathName", uploadPathName);
					rtnMap.put("saveFileName", saveFileName);

					rtnMap.put("originalFileName", originalFileName);
					rtnMap.put("originalFileExt", originalFileExt);

					Map<String, String> sizeMap = getImgFileSize(saverDirPath + saveFileName);
					rtnMap.putAll(sizeMap);
				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rtnMap;
	}

	/**
	 * 이미지 파일정보 조회
	 * 
	 * @param imgFilePath
	 * @return
	 */
	public static Map<String, String> getImgFileSize(String imgFilePath) {

		Map<String, String> rtnMap = new HashMap<String, String>();

		try {
			File file = new File(imgFilePath);
			BufferedImage bi = ImageIO.read(file);
			int imgWidth = bi.getWidth();
			int imgHeight = bi.getHeight();
			long fileSize = file.length();

			rtnMap.put("imgWidth", String.valueOf(imgWidth));
			rtnMap.put("imgHeight", String.valueOf(imgHeight));
			rtnMap.put("fileSize", String.valueOf(fileSize));

		} catch (Exception e) {

		}

		return rtnMap;
	}

	/**
	 * 썸네일 이미지지 저장
	 * 
	 * @param filePathName
	 * @param maxWidth
	 * @param maxHeight
	 */

	public static String thumbImage(String uploadPathName, String fileName, String fileExt, int maxWidth, int maxHeight) throws Exception {

		String thumbFileName = "";

		try {
			File file = new File(uploadPathName + fileName);
			InputStream inputStream = new FileInputStream(uploadPathName + fileName);
			Image img = new ImageIcon(file.toString()).getImage(); // 파일 정보 추출

			int width = img.getWidth(null);
			int height = img.getHeight(null);

			if (width > maxWidth) {
				float widthRatio = maxWidth / (float) width;
				width = (int) (width * widthRatio);
				height = (int) (height * widthRatio);
			}

			if (height > maxHeight) {
				float heightRatio = maxHeight / (float) height;
				width = (int) (width * heightRatio);
				height = (int) (height * heightRatio);
			}

			BufferedImage resizedImage = imageResize(inputStream, width, height);
	
			ImageIO.write(resizedImage, fileExt, new File(uploadPathName + "thumb_" + fileName));
	
			thumbFileName = "thumb_" + fileName;
		} catch (Exception e) {
			System.out.println("This image can not be resized. Please check the path and type of file.");
		}
		return thumbFileName;
	}

	public static BufferedImage imageResize(InputStream inputStream, int width, int height) throws IOException {

		BufferedImage inputImage = ImageIO.read(inputStream); 

		BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
		// 입력받은 리사이즈 길이와 높이

		Graphics2D graphics2D = outputImage.createGraphics();
		graphics2D.drawImage(inputImage, 0, 0, width, height, null); 
		graphics2D.dispose(); 

		return outputImage;
	}
	
	/*******************/
	/**
	 * 이미지파일 업로드
	 *
	 *
	 * @return
	 */

	public static Map<String, String> joinImageUpload(HttpServletRequest request, MultipartFile[] uploadFile, String fileId) {

		String requestUrl = request.getRequestURL().toString();
		String uploadPathName = "/uploads/media/"+fileId+"/";
		String saverDirPath = "/app"+ uploadPathName;
		

		if (requestUrl.indexOf("localhost") > -1) {
			saverDirPath = "C:\\Temp\\uploads\\media\\"+fileId+"\\";
		}

		Map<String, String> rtnMap = new HashMap<String, String>();

		try {

			File fileDir = new File(saverDirPath);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}

			String fileName = "";
			Map<String, Object> result = new HashMap<>();
			for (MultipartFile multipartFile : uploadFile) {

				String originalFileName = multipartFile.getOriginalFilename();
				String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

				String saveFileName = originalFileName;

				File saveFile = new File(saverDirPath, saveFileName);
				try {
					multipartFile.transferTo(saveFile);
					rtnMap.put("filePathName", uploadPathName);
					rtnMap.put("saveFileName", saveFileName);

					rtnMap.put("originalFileName", originalFileName);
					rtnMap.put("originalFileExt", originalFileExt);

					Map<String, String> sizeMap = getImgFileSize(saverDirPath + saveFileName);
					rtnMap.putAll(sizeMap);
				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rtnMap;
	}

	
	
}
