package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogdao;
	
	public BlogVo getcontent(String id) {
		System.out.println("blogservice: getcontent");
		return blogdao.getcontent(id);
	}
	
	public String chgbasic(BlogVo blogvo, MultipartFile file) {
		//파일카피
		String saveDir = "c:\\javaStudy\\upload";
		//원파일 이름 추출
		
		String orName = file.getOriginalFilename();
		System.out.println("orName :" + orName);
		
		//확장자유지
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName :" + exName);
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : "+ saveName);
		//파일경로
		String filepath = saveDir+"\\"+saveName;
		System.out.println("filepath : "+ filepath);
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("filesize : "+ fileSize);
		
		//파일 서버에 복사.
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filepath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blogvo.setLogoFile(saveName);
		blogdao.chgbasic(blogvo);
		
		//파일에서 필요정보 추출 및 DB저장
		
		return saveName;

	}

	public List<CategoryVo> list() {
		System.out.println("blogservice: list");
		return blogdao.list();
	}
	public List<PostVo> plist(int cateNo){
		System.out.println("blogservice : plist");
		return blogdao.plist(cateNo);
	}
	
	public CategoryVo insertcate(CategoryVo categoryvo) {
		System.out.println("blogservice: insertcate");
		blogdao.insertcate(categoryvo);
		categoryvo.setCount(blogdao.count());
		int cateNo = categoryvo.getCateNo();
		return blogdao.selectbyNo(cateNo);
	}
	
	public int delete(int cateNo) {
		System.out.println("blogservice: delete");
		return blogdao.delete(cateNo);
	}
	
	public CategoryVo count(int cateNo) {
		
		return blogdao.selectbyNo(cateNo);
		
	}

	public int write(PostVo postvo) {
		System.out.println("blogservice: write");
		return blogdao.write(postvo);
	}
	
	
}
