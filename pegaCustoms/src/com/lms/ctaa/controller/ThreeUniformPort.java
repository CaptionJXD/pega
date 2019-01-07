package com.lms.ctaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("threeUniformPort")
public class ThreeUniformPort {
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("", "");
		modelAndView.setViewName("/threeUniformPort/showList1");
		return modelAndView;
	}
	
	@RequestMapping("/showList")
	public String showList(){
		return "/threeUniformPort/list";
	}
	
	@RequestMapping("/listInfo")
	public @ResponseBody List<Object> listInfo(){
		List<Object> list = new ArrayList<>();//this.userService.getUserList();
		return list;
	}
}
