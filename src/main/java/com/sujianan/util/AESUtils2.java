package com.sujianan.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtils2 {
	 
	private static final String ENCODING = "UTF-8";
 
	private static final String PASSWORD = "GZth+UbTiNkbmiAsAgQuyg=="; // 加密秘钥
 
	/**
	 * AES加密
	 *
	 * @param content
	 *            明文
	 * @return 密文
	 * @throws Exception 
	 */
	public static String encryptAES(String content) throws Exception {
		if (content == null || "".equals(content)) {
			throw new Exception("明文不能为空！");
		}
		byte[] encryptResult = encrypt(content, PASSWORD);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		// BASE64位加密
		encryptResultStr = ebotongEncrypto(encryptResultStr);
		return encryptResultStr;
	}
 
	/**
	 * AES解密
	 *
	 * @param encryptResultStr
	 *            密文
	 * @return 明文
	 * @throws Exception 
	 */
	public static byte[] decryptAES(byte[] decryptFrom) throws Exception {
		// BASE64位解密
		try {
			byte[] decryptResult = decrypt(decryptFrom, PASSWORD);
			return decryptResult;
		} catch (Exception e) { // 当密文不规范时会报错，可忽略，但调用的地方需要考虑
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 加密字符串
	 */
	private static String ebotongEncrypto(String str) {
		BASE64Encoder base64encoder = new BASE64Encoder();
		String result = str;
		if (str != null && str.length() > 0) {
			try {
				byte[] encodeByte = str.getBytes(ENCODING);
				result = base64encoder.encode(encodeByte);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// base64加密超过一定长度会自动换行 需要去除换行符
		return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
	}
 
	/**
	 * 解密字符串
	 */
	private static String ebotongDecrypto(String str) {
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] encodeByte = base64decoder.decodeBuffer(str);
			return new String(encodeByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
 
	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 注意这句是关键，防止linux下 随机生成key。用其他方式在Windows上正常，但Linux上会有问题
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
 
	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
 
	public static void main(String[] args) throws Exception {
		byte[] buffer = null;  
        try {  
        	File  tsfile = new File("D:\\new.ts");
    		FileInputStream fis = new FileInputStream(tsfile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();
            bos.close();
            buffer = bos.toByteArray();  
            byte[] decrypt = decrypt(buffer, PASSWORD);
            File f = new File("D:\\abc.ts");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(decrypt);
            fos.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
		byte[] bbb = decryptAES(buffer);
		File f = new File("D:\\abc.ts");
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bbb);
        fos.close();
	}
 
}
