package com.javaex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;



@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping("/{id}")
	public String main(Model model,
			@PathVariable("id") String id){
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		System.out.println("blogcontroller: blog-main");
		System.out.println(blogvo);

		return "blog/blog-main";
	}

	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable("id") String id,
						Model model) {
		System.out.println("/admin/basic");
		
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		
		
		return "blog/admin/blog-admin-basic";
	}
	@RequestMapping("/{id}/admin/chgbasic")
	public String chgbasic(@PathVariable("id") String id,
						   @RequestParam("blogTitle")String blogTitle,
						   @RequestParam("file") MultipartFile file) {
			System.out.println("blogcontroller: chgbasic");
			BlogVo blogvo = new BlogVo();
			blogvo.setBlogTitle(blogTitle);
		return "redirect:/{id}";
	}
	

	@RequestMapping("/{id}/admin/cate")
	public String cate(@PathVariable("id") String id,
					   Model model) {
		System.out.println("/admin/basic");
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping("/{id}/admin/insertcate")
	public String insertcate(@PathVariable("id") String id,
							 Model model) {
		return "";
	}

	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id,
					    Model model) {
		System.out.println("/admin/basic");
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		
		return "blog/admin/blog-admin-write";
	}

}
