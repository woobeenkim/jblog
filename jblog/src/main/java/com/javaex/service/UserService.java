package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public void join(UserVo uservo) {
		System.out.println("userservice: join");
		System.out.println(uservo);
		userdao.join(uservo);
	}
	
	public void blogjoin(BlogVo blogvo) {
		System.out.println("userservice: blogjoin");
		System.out.println(blogvo);
		userdao.blogjoin(blogvo);
	}
	
	public void catejoin(CategoryVo categoryvo) {
		System.out.println("userservice: catejoin");
		System.out.println(categoryvo);
		userdao.catejoin(categoryvo);
	}
	
	public boolean getid(String id) {
		System.out.println("userservice:getid");
		UserVo uservo = userdao.getid(id);
		boolean result = true;
		if(uservo == null) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
	
	public UserVo login(UserVo uservo) {
		System.out.println("userservice: login");
		UserVo authUSer = userdao.selectUser(uservo);
		return authUSer;
	}
}
