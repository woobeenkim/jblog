package com.javaex.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;



@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping("/{id}")
	public String main(Model model,
					   @PathVariable("id") String id,
					   @ModelAttribute PostVo postvo){
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		System.out.println("blogcontroller: blog-main");
		List<CategoryVo> cateList = blogservice.list();
		model.addAttribute("cateList",cateList);
		List<PostVo> pList = blogservice.plist(postvo.getCateNo());
		model.addAttribute("pList",pList);
		System.out.println(pList.toString());
		
		System.out.println(postvo);
		return "blog/blog-main";
	}
	

	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable("id") String id,
						Model model) {
		System.out.println("/admin/basic");
		
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		System.out.println(blogvo);
		
		return "blog/admin/blog-admin-basic";
	}
	@RequestMapping("/{id}/admin/chgbasic")
	public String chgbasic(@PathVariable("id") String id,
						   @RequestParam("blogTitle")String blogTitle,
						   @RequestParam("file") MultipartFile file) {
			System.out.println("blogcontroller: chgbasic");
			BlogVo blogvo = new BlogVo();
			blogvo.setBlogTitle(blogTitle);
			blogvo.setId(id);
			System.out.println(blogvo);
			blogservice.chgbasic(blogvo, file);
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
	
	@ResponseBody
	@RequestMapping("/list")
	public List<CategoryVo> list(Model model) {
		List<CategoryVo> cateList = blogservice.list();
		System.out.println("guestController:ajaxlist");
		System.out.println(cateList.toString());
		return cateList;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/insertcate")
	public CategoryVo insertcate(@PathVariable("id") String id,
								 @ModelAttribute CategoryVo categoryvo) {
		System.out.println("guestController:insertcate");
		System.out.println(categoryvo);
		CategoryVo vo = blogservice.insertcate(categoryvo);
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/catedelete")
	public int delete(@PathVariable("id") String id,
					  @RequestParam("cateNo")int cateNo,
					  Model model) {
		System.out.println(cateNo);
		CategoryVo categoryvo = blogservice.count(cateNo);
		
		int count = categoryvo.getCount();
		if(count < 0) {
		blogservice.delete(cateNo);
		}
		return count;
	}

	@RequestMapping("/{id}/admin/wform")
	public String wform(@PathVariable("id") String id,
					    Model model) {
		System.out.println("/admin/basic");
		BlogVo blogvo = blogservice.getcontent(id);
		model.addAttribute("blogvo", blogvo);
		List<CategoryVo> cateList = blogservice.list();
		model.addAttribute("cateList",cateList);
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id,
						@ModelAttribute PostVo postvo) {
		
		System.out.println("bloscontroller: write");
		blogservice.write(postvo);
		return "redirect:/{id}";
	}
	
	

}
