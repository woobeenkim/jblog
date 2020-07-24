package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogdao;
	
	public BlogVo getcontent(String id) {
		System.out.println("blogservice: getcontent");
		return blogdao.getcontent(id);
	}
	
	public BlogVo getcate(String id) {
		System.out.println("blogservice: getcontent");
		return blogdao.getcate(id);
	
	}
	
	public int insertcate(String id) {
		System.out.println("blogservice: getcote");
		return blogdao.insertcate(id);
	}
	
	
}
