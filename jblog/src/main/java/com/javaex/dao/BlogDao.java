package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getcontent(String id) {
		System.out.println("blogdao: getcontent");
		return sqlSession.selectOne("blog.getcontent",id);
	}
	
	public void chgbasic(BlogVo blogvo) {

		System.out.println("blogdao: chgbasic");
		System.out.println(blogvo);
		sqlSession.update("blog.chgbasic", blogvo);
		}
	
	public int insertcate(CategoryVo categoryvo) {
		System.out.println("blogdao: insertcate");
		return sqlSession.insert("blog.insertcate",categoryvo);
	}
	
	public CategoryVo selectbyNo(int cateNo) {
		return	sqlSession.selectOne("blog.selectbyNo", cateNo);
	}
	
	public List<CategoryVo> list(){
		System.out.println("blogdao: list");
		return sqlSession.selectList("blog.list");
	}
	
	public List<PostVo> plist(int cateNo){
		System.out.println("blogdao: plist");
		return sqlSession.selectList("blog.plist", cateNo);
	}
	
	public int count() {
		return sqlSession.selectOne("blog.count");
	}
	
	public int delete(int cateNo) {
		System.out.println("blogdao: delete");
		return sqlSession.delete("blog.delete", cateNo);
	}
	

	public int write(PostVo postvo) {
		System.out.println("blogdao. write");
		System.out.println(postvo);
		return sqlSession.insert("blog.write", postvo);
	}
	
	
}
