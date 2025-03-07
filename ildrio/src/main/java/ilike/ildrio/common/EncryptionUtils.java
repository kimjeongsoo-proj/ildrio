package ilike.ildrio.common;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encryption MD5(Message-Digest algorithm 5) SHA1(Secure Hash Standard
 * Algorithm 1) AES(Advanced Encryption Standard) Sample
 * 
 * Algorithm MD5 String password = ""; String encPassword =
 * EncryptionUtils.encryptMd5(password);
 * 
 * Algorithm SHA1 String password = ""; String encPassword =
 * EncryptionUtils.encryptSha1(password);
 * 
 * Algorithm AES Encrypt String message = ""; String encSsn =
 * EncryptionUtils.encryptAES(message);
 * 
 * Algorithm AES Decrypt String message = ""; String encSsn =
 * EncryptionUtils.decryptAES(message);
 */
public class EncryptionUtils {

	public static void main(String[] args) throws Exception {

		String sha256 = encryptSha256("abc135@@");
		System.out.println(sha256);
	}

	/**
	 * MD5(Message-Digest algorithm 5)
	 * 
	 * @param inputStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptMd5(String inputStr) throws NoSuchAlgorithmException {
		String rtnStr = "";
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] digest = messageDigest.digest(inputStr.getBytes());
		//rtnStr = Hex.encodeHexString(digest);
		return rtnStr;
	}

	/**
	 * SHA1(Secure Hash Standard Algorithm 1) producing a 160bit digest(40 hex
	 * numbers) from any data with a maximum size of 264bits jdk1.4 over
	 * 
	 * @param inputStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSha1(String inputStr) throws NoSuchAlgorithmException {
		String rtnStr = "";
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] digest = messageDigest.digest(inputStr.getBytes());
		//rtnStr = Hex.encodeHexString(digest);
		return rtnStr;
	}

	public static String encryptSha256(String inputStr) throws NoSuchAlgorithmException {

		String rtnStr = "";
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		sh.update(inputStr.getBytes());
		byte[] byteData = sh.digest();
		StringBuilder sb = new StringBuilder();

		for (byte byteDatum : byteData) {
			sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
		}
		rtnStr = sb.toString();

		return rtnStr;
	}

	/**
	 * encryption
	 * 
	 * @param encType
	 *            : MD5, SHA1
	 * @param inputStr
	 *            :
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrypt(String encType, String inputStr) throws NoSuchAlgorithmException {
		String rtnStr = "";
		MessageDigest messageDigest = MessageDigest.getInstance(encType);
		byte[] byteData = inputStr.getBytes();
		messageDigest.update(byteData);
		byte[] digest = messageDigest.digest();
		//rtnStr = Hex.encodeHexString(digest);
		return rtnStr;
	}

	/*
	 * AES 128 Alogrithm begginning 아래의 모듈을 통하여 암호활 경우 리턴되는 값의 길이는 다음과 같습니다.
	 * 번호(13BYTE) => 32BYTE 신용카드번호(14BYTE) => 32BYTE 신용카드번호(15BYTE) => 32BYTE
	 * 신용카드번호(16BYTE) => 64BYTE 계좌번호(?) => 32BYTE 따라서 용도에 따라 DB저장 시 컬럼의 크기를 32BYTE
	 * 또는 64BYTE 이상으로 설정해주시기 바랍니다.
	 */
	// 다음은 AES방식을 사용하는데 공통키를 이용하여 특정조건에서 정해진 seed를 생성하게 하는 방법이다.
	private final static String passwd = "비밀번호";
	private final static byte[] rawKey = new byte[] {};

	/**
	 * AES KEY Generator passwd 가 변경될 경우 아래의 메소드를 실행하여 재생성해야한다. 모듈 효율성을 위해 생성된 키값을
	 * 미리 선전하여 키생성 로직을 제외
	 * 
	 * @return
	 * @throws Exception
	 */
	private static byte[] getRawKey() throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] seed = passwd.getBytes();
		sr.setSeed(seed);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}

	public static String encryptAES(String text,String key, String iv) throws Exception {
		String alg = "AES/CBC/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptAES(String cipherText, String key, String iv) throws Exception {
    	String alg = "AES/CBC/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}