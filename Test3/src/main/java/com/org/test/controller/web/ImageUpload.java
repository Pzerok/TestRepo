package com.org.test.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ImageUpload {
	
	@RequestMapping(value="/imageUpload.do")
	public String imageUpload(
			MultipartHttpServletRequest multiFile,
			HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		
		long limitSize = 1024 * 1024 * 2; // 2MB
		MultipartFile file = multiFile.getFile("upload");
		if(file != null && file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
			if(file.getContentType().toLowerCase().startsWith("image/")) {
				if(file.getSize() > limitSize) {
					JsonObject jsonMsg = new JsonObject();
					JsonArray jsonArr = new JsonArray();
					jsonMsg.addProperty("message", "2MB미만의 이미지만 업로드 가능합니다.");
					jsonArr.add(jsonMsg);
					json.addProperty("uploaded", 0);
					json.add("error", jsonArr.get(0));
					resp.setCharacterEncoding("UTF-8");
					printWriter = resp.getWriter();
					printWriter.println(json);
				}else {
					try {
						String fileName = file.getName();
						byte[] bytes = file.getBytes();
						String uploadPath = "http://192.168.144.20/resources/img";
						File uploadFile = new File(uploadPath);
						if(!uploadFile.exists()) {
							uploadFile.mkdirs();
						}
						
						fileName = UUID.randomUUID().toString();
						uploadPath = uploadPath + "/" + fileName + ".jpg";
						out = new FileOutputStream(new File(uploadPath));
						out.write(bytes);
						
						printWriter = resp.getWriter();
						resp.setContentType("text/html");
						// 최종 획득된 업로드한 이미지 경로(URL)
						String fileUrl = "http://192.168.144.20/resources/img/" + fileName + ".jpg";
						
						json.addProperty("uploaded", 1);
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);
						
						printWriter.println(json);
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(out != null) {
							out.close();
						}
						if(printWriter != null) {
							printWriter.close();
						}
					}
				}
			}
		}
		return null;
	}
	
}
