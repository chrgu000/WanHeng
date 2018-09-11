package com.cgwas.common.listener;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static Boolean uploadFile(MultipartFile file, String path) {
		// 保存
		try {
			System.out.println("开始");
			FileOutputStream os = new FileOutputStream(path);
			InputStream in = file.getInputStream();
			byte[] buffer = new byte[1024 * 1024 * 4];
			int i = -1;
			while ((i = in.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
			os.flush(); // 关闭流
			in.close();
			os.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
