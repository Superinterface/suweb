package com.sujianan.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static void filePathCreate(String filePath, String createPath) throws IOException {
		if (!filePath.equals(createPath)) {
			filePath = filePath.replace("\\", "/");
			if (createPath != null && !"".equals(createPath)) {
				File file = new File(createPath);
				if (!file.exists()) {
					file.mkdir();
				}

				int index = filePath.indexOf("/", createPath.length() + 1);
				createPath = filePath.substring(0, index >= 0 ? index : filePath.length());
			} else {
				String dirPath = filePath.substring(0, filePath.indexOf("/"));
				File rootDir = new File(dirPath);
				if (!rootDir.exists()) {
					rootDir.mkdir();
				}

				createPath = filePath.substring(0, filePath.indexOf("/", dirPath.length() + 1));
			}

			filePathCreate(filePath, createPath);
		}
	}

	public static void main(String[] args) throws IOException {
		String asd = "d:/asd/a/s/d/b.txt";
		filePathCreate(asd, (String) null);
	}
}