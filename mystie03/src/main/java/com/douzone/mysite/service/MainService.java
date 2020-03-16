package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.MainRepository;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.MainVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class MainService {
	@Autowired
	private MainRepository mainRepository;
	
	private static final String SAVE_PATH = "/mysite-uploads";
	private static final String URL = "/images";
	
	
	
	public String restore(MainVo vo) {
		String url = "";
		try {
			MultipartFile multipartFile = vo.getMultipartFile();
			if(multipartFile.isEmpty()) {
			vo.setProfile(mainRepository.find().getProfile());
			System.out.println("profile = "+vo.getProfile());
			 mainRepository.update(vo);
			 return "url";
			}
			
		String originFilename = multipartFile.getOriginalFilename();
		String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
		
		String saveFilename = generateSeaveFilename(extName);
		long fileSize = multipartFile.getSize();
		
		
		System.out.println("### saveFilename = " + saveFilename);
		System.out.println(" ### name = "+ originFilename + " ### fileSize : "+ fileSize);
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		os.write(fileData);
		os.close();
		url = URL + "/" + saveFilename;
		vo.setProfile(url);
		mainRepository.update(vo);
		
	}catch(IOException ex) {
		throw new RuntimeException("file upload error:" + ex);
	}
		return url;
}

	private String generateSeaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("."+extName);
		return filename;
	}

	public MainVo find() {
		return mainRepository.find();
	}





}
