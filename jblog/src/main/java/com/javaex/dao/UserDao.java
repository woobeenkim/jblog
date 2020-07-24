package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;
@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int join(UserVo uservo) {
		int no = sqlSession.insert("user.join", uservo);
		return no;
	}
	
	public int blogjoin(BlogVo blogvo) {
		return sqlSession.insert("user.blogjoin", blogvo);
	}
	
	public int catejoin(CategoryVo categoryvo) {
		return sqlSession.insert("user.catejoin", categoryvo);
	}
	
	public UserVo getid(String id) {
		System.out.println(id);
		System.out.println("userdao:getId");
		return sqlSession.selectOne("user.getId", id);
		
	}
	
	public UserVo selectUser(UserVo uservo) {
		
		System.out.println("userdao:selectUser");
		return sqlSession.selectOne("user.selectUser",uservo);
	}
}
