package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	
	@RequestMapping("/jform")
	public String joinForm() {
		System.out.println("user/jform");
		return "user/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo uservo) {
		
		userservice.join(uservo);
		BlogVo blogvo = new BlogVo();
		blogvo.setLogoFile("default");
		blogvo.setId(uservo.getId());
		blogvo.setBlogTitle(uservo.getUserName());
		blogvo.setUserName(uservo.getUserName());
		userservice.blogjoin(blogvo);
		CategoryVo categoryvo = new CategoryVo();
		categoryvo.setId(blogvo.getId());
		categoryvo.setCateName("미분류");
		categoryvo.setDescription("");
		userservice.catejoin(categoryvo);

		
		return "redirect:/user/joinSuc";
	}
	
	@RequestMapping("/joinSuc")
	public String joinSuc() {
		System.out.println("/user/joinSuccess");
		return "user/joinSuccess";
	}
	
	@RequestMapping("/lform")
	public String lform() {
		return "user/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo uservo, HttpSession session,
						Model model) {
		System.out.println("usercontroller/login");
		
		UserVo authUser = userservice.login(uservo);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			model.addAttribute("authUser",authUser);
			System.out.println(authUser);
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/user/lform?result=fail";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(@ModelAttribute UserVo uservo,
						 HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping("/idcheck")
	public boolean idcheck(Model model,
						  @RequestParam("userId") String id) {
		System.out.println(id);
		boolean result = userservice.getid(id);

		return result;
	}
}
	
	
