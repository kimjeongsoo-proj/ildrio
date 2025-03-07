package ilike.ildrio.common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil {

	public static void main(String[] args) throws Exception {

		//TO DO

	}

	/**
	 * 파일 존재 여부
	 * @param imageUrl
	 * @return
	 */
	public static boolean checkImageExistence(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();
			return (responseCode == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Map<String, String> imgFileDownload(String imgSavePath, String imgSrcUrl, String goods_id) {
		Map<String, String> rsMap = new HashMap<String, String>();

		String dn_file_nm = imgSrcUrl.substring(imgSrcUrl.lastIndexOf('/') + 1, imgSrcUrl.length());

		dn_file_nm = dn_file_nm.replace(" ", "_").replace("[", "_").replace("]", "");

		//StringUtil.SysOut("dn_file_nm " + dn_file_nm +" >>> "+imgSrcUrl);

		String file_ext = dn_file_nm.substring(dn_file_nm.lastIndexOf('.') + 1, dn_file_nm.length());

		BufferedImage image = null;
		try {

			boolean imageExists = checkImageExistence(imgSrcUrl);
			if (imageExists) {
				//System.out.println("이미지가 존재합니다.");

				// 디렉토리가 없으면 생성
				File desti = new File(imgSavePath);
				if (!desti.exists()) {
					desti.mkdir();
				}

				image = ImageIO.read(new URL(imgSrcUrl));
				BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);

				Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
				// graphics.setBackground(Color.WHITE);
				graphics.drawImage(image, 0, 0, null);

				ImageIO.write(bufferedImage, file_ext, new File(imgSavePath + "/" + dn_file_nm));
				//System.out.println(dn_file_nm + " 다운완료");

				rsMap.put("dnFileName",dn_file_nm);
				rsMap.put("resultCode", "SUCCESS");

			} else {
				rsMap.put("dnFileName", "NOT EXISTS");
				rsMap.put("resultCode", "NOT EXISTS");
				System.out.println("이미지가 존재하지 않습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (image != null) {
				image = null;
			}
		}
		return rsMap;
	}

	/*********************************/

	public static Map<String, String> getImageSize(String urlPath) {

		String imgWidth = "";

		Map<String, String> rtnMap = new HashMap<String, String>();

		try {

			URL url = new URL(urlPath);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();

			if (urlCon.getResponseCode() == 200) {

				BufferedImage image = ImageIO.read(new URL(urlPath));

				rtnMap.put("imgWidth", String.valueOf(image.getWidth()));
				rtnMap.put("imgHeight", String.valueOf(image.getHeight()));
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return rtnMap;
	}

	public static String procImgFormatChange(String imgPath, String imgFileName, String frFormat, String toFormat) {

		String rtn = "";

		BufferedImage bufferedImage;

		try {

			String originalImgFileNm = imgPath + "/" + imgFileName;

			// read image file
			bufferedImage = ImageIO.read(new File(originalImgFileNm));

			// create a blank, RGB, same width and height, and a white
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			String newFileName = imgFileName.substring(0, imgFileName.lastIndexOf('.')) + "." + toFormat;

			String newImgPathFileName = imgPath + "/" + newFileName;

			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", new File(newImgPathFileName));

			rtn = newImgPathFileName;

		} catch (IOException e) {

			e.printStackTrace();

			rtn = imgPath + "/" + imgFileName;

		}

		return rtn;
	}

	public static void resizeImage(String filePathName, int maxWidth, int maxHeight) {

		BufferedImage src, dest;

		ImageIcon icon;

		try {

			src = ImageIO.read(new File(filePathName));

			int width = src.getWidth();

			int height = src.getHeight();

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

			dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			Graphics2D g = dest.createGraphics();

			AffineTransform at = AffineTransform.getScaleInstance((double) width / src.getWidth(), (double) height / src.getHeight());

			g.drawRenderedImage(src, at);

			icon = new ImageIcon(dest);

		} catch (Exception e) {

			//System.out.println("This image can not be resized. Please check the path and type of file.");

		}

	}

	public static int getImgFileSize(String imgFilePath) {

		int imgWidth = 0;

		try {
			File file = new File(imgFilePath);
			BufferedImage bi = ImageIO.read(file);
			imgWidth = bi.getWidth();

			//System.out.println(imgFilePath+" >>> "+bi.getWidth()+"x"+bi.getHeight());
		} catch (Exception e) {
			imgWidth = 0;
		}

		return imgWidth;
	}

}
