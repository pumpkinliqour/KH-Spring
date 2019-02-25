package com.kh.spring.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

@Controller
public class DemoController {

	@Autowired
	DemoService service;
	
	
	
	@RequestMapping("/demo/updateDevEnd.do")
	public String updateEnd(Dev dev) 
	{
		int result=service.updateDev(dev);
		
		return "redirect:/demo/selectDemoList.do"; 
	}
	
	
	@RequestMapping("/demo/updateDev.do")
	public String updateDev(int no, Model model)
	{
		Dev dev=service.selectOne(no);
		model.addAttribute("dev",dev);
		return "demo/form";
		
	}
	
	@RequestMapping("/demo/deleteDev.do")
	public ModelAndView deleteDev(ModelAndView mv, int no) {
		
		int result=service.deleteDev(no);
		
		String msg="";
		String loc="/demo/selectDemoList.do";
		
		if(result>0) {
			msg="성공";
		}
		else {
			msg="실패";
		}
		
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	@RequestMapping("/demo/selectDemoList.do")
	public String selectDemoList(Model model) {
		List<Dev> list=service.selectDemoList();
		System.out.println(list);
		model.addAttribute("list",list);
		return "demo/devList";
	}
	
	
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		/*service.test();*/
		System.out.println("/demo/demo.do/controller실행!");
		//prefix+return값+suffix
		return "demo/demo";
	}
	
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest request)
	{
		String devName=request.getParameter("devName");
		int devAge=Integer.parseInt(request.getParameter("devAge"));
		String devEmail=request.getParameter("devEmail");
		String devGender=request.getParameter("devGender");
		String[] devLang=request.getParameterValues("devLang");
		
		Dev dev=new Dev(devName, devAge, devEmail,devGender,devLang);
		request.setAttribute("dev", dev);
		
		System.out.println(devName+devAge+devEmail+devGender+devLang);
		
		return "demo/result";
	}
	
	@RequestMapping("/demo/demo2.do")
	public String demo2(HttpServletRequest req,
					@RequestParam(value="devName" ) String devName,
					@RequestParam(value="devAge", required=false, defaultValue="19") int devAge,
					@RequestParam(value="devEmail", required=false) String devEmail,
					@RequestParam(value="devGender", required=false) String devGender,
					@RequestParam(value="devLang", required=false) String[] devLang
					) 
	{
		req.setAttribute("dev", new Dev(devName,devAge,devEmail,devGender,devLang));
		
		System.out.println(devName+devAge+devEmail+devGender+devLang);
		return "demo/result";
	}
	
	@RequestMapping("/demo/demo3.do")
	public String demo3(Dev dev, Model model)
	{
		System.out.println(dev);
		model.addAttribute("dev",dev);
		//System.out.println(model);
		return "demo/result";
	}
	
	@RequestMapping("/demo/insertDev.do")
	public String insertDev(Dev dev)
	{
		int result=service.insertDev(dev);
		
		return "redirect:/";
	}
	
	
}



