package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class BlogDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getcontent(String id) {
		System.out.println("blogdao: getcontent");
		return sqlSession.selectOne("blog.getcontent",id);
	}
	
	public BlogVo getcate(String id) {
		System.out.println("blogdao: getcontent");
		return sqlSession.selectOne("blog.getcate",id);
	}
	
	public int insertcate(String id) {
		System.out.println("blogdao: insertcate");
		return sqlSession.insert("blog.insertcate",id);
	}
}
