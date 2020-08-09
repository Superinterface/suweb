package com.sujianan.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	/**
     * AES加密字符串
     * 
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */ 
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return result;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解密AES加密过的字符串
     * 
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);  
            return result; // 明文   
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
		// 
		FileInputStream fis1 = new FileInputStream(new File("D:\\encryption.key"));
		ByteArrayOutputStream bos1 = new ByteArrayOutputStream(1000);  
		byte[] a1 = null;
		byte[] b1 = new byte[1000];  
        int n1;  
        while ((n1 = fis1.read(b1)) != -1) {  
            bos1.write(b1, 0, n1);  
        } 
        a1 = bos1.toByteArray();
        bos1.close();
        fis1.close();
        String pwd = "GZth+UbTiNkbmiAsAgQuyg==";
//        String pwd = new String(a1,"utf-8");
		// 读取文件二进制流
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
            byte[] decrypt = decrypt(buffer, pwd);
            File f = new File("D:\\abc.ts");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(decrypt);
            fos.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
    
    
//    public static void main(String[] args) throws Exception {
//    	// 读取文件字节
//		String content = "密码1993";
//		String password = "1234";
//		System.out.println("需要加密的内容：" + content);
//		byte[] encrypt = encrypt(content, password);
//		System.out.println("加密后的2进制密文：" + new String(encrypt));
//		String hexStr = ParseSystemUtil.parseByte2HexStr(encrypt);
//		System.out.println("加密后的16进制密文:" + hexStr);
//		byte[] byte2 = ParseSystemUtil.parseHexStr2Byte(hexStr);
//		System.out.println("加密后的2进制密文：" + new String(byte2));
//		byte[] decrypt = decrypt(byte2, password);
//		System.out.println("解密后的内容：" + new String(decrypt,"utf-8"));
//	}

}
