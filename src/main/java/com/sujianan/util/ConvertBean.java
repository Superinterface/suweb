package com.sujianan.util;

import java.io.File;
import java.io.IOException;

import com.sujianan.exception.ConvertBeanException;

/**
 * 
 * @author		SuperInterface
 * @github		SuperInterface
 * @date		2020年1月15日
 *
 */
public class ConvertBean {
	
	private static String osName = System.getProperty("os.name");
	
	/**
	 * use lireOffice command convert file to pdf
	 * word convert pdf need word path and pdf file save path use lireOffice convert it(word)
	 * such as :	wordPath /usr/local/office/1.docx
	 * 				pdfPath	 /usr/local/office/
	 * use code convert in pdfPath create xxx/1.pdf
	 */
	@SuppressWarnings("unused")
	public static void wordConvertPdf(String wordPath, String pdfPath) {
		System.out.println("===== convert action =====");
		if(wordPath == null || pdfPath == null || "".equals(wordPath.trim()) || "".equals(pdfPath.trim()) || !new File(wordPath).exists()) {
			throw new ConvertBeanException("path error, possible reasons path can't be null or file don't exists");
//			System.out.println("path error, possible reasons path can't be null or file don't exists");
		}
		
		StringBuffer command = new StringBuffer();
		StringBuffer tryCommand = new StringBuffer();
		Process process = null;
		Process tryProcess = null;
		int excuteStatus = -1;
		int excuteExitStatus = -1;
		
		if(osName.toUpperCase().contains("WINDOWS")) {
			tryCommand.append("soffice --headless --accept=\"socket,host=127.0.0.1,port=8100;urp;\" --nofirststartwizard &");
			command.append("soffice --convert-to pdf:writer_pdf_Export ").append(wordPath).append(" --outdir ").append(pdfPath);
		}else if (osName.toUpperCase().contains("LINUX")){
			tryCommand.append("soffice --headless --accept=\"socket,host=127.0.0.1,port=8100;urp;\" --nofirststartwizard &");
			command.append("soffice --invisible --convert-to pdf:writer_pdf_Export ").append(wordPath).append(" --outdir ").append(pdfPath);
		}
		
		System.out.println("===== command is "+command);
		System.out.println("===== tryCommand is "+tryCommand);
		
		try {
			process = Runtime.getRuntime().exec(command.toString());
			excuteStatus = process.waitFor();
			excuteExitStatus = process.exitValue();
			if(excuteStatus != 0) {
				System.out.println("convert failed try start office");
				// try start office
				tryProcess = Runtime.getRuntime().exec(tryCommand.toString());
				if(tryProcess.waitFor() == 0) {
					process = Runtime.getRuntime().exec(command.toString());
					excuteStatus = process.waitFor();
					System.out.println(excuteStatus == 0 ? "convert success" : "convert failed");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=====excuteStatus" + excuteStatus + " ====="+"=====excuteExitStatus" + excuteExitStatus + "=====");
		System.out.println("convert success use time is");
		process.destroy();
        process = null;
	}
//	public static void main(String[] args) {
//		if(osName.toUpperCase().contains("WINDOWS")) {
//			wordConvertPdf("D:\\xxx.docx", "D:/a/b/c/g");
//		}else {
//			wordConvertPdf("/xxx.docx", "/a");
//		}
//	}
}
