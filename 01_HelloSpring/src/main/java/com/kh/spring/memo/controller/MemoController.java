package com.kh.spring.memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	
	private Logger logger=Logger.getLogger(MemoController.class); //로거라는 객체가 하나 생성이 됨
	
	@Autowired
	MemoService service;

	@RequestMapping("/memo/memo.do")
	public String memo(Model model) {
		
		List<Memo> memoList=service.selectMemoList();
		
		model.addAttribute("memoList", memoList);
		
		return "memo/memo";
	}
	
	@RequestMapping("/memo/insertMemo.do")
	public String insertMemo(String memo, String password, Model model) {
		
		Map<String,String> map=new HashMap<String, String>();
		
		logger.debug("memo : "+memo+" / "+"password : "+password);
		
		map.put("memo", memo);
		map.put("password",password);
		
		int result=service.insertMemo(map);
		
		String msg="";
		String loc="/memo/memo.do";
		
		if(result>0) {
			msg="성공";
		}
		else {
			msg="실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
	
	
	@RequestMapping("/memo/deleteMemo.do")
	public String deleteMemo(int memoNo, Model model) {
		
		logger.debug("memoNo : "+memoNo);
		
		int result=service.deleteMemo(memoNo);
		
		String msg="";
		String loc="/memo/memo.do";
		
		if(result>0) {
			msg="성공";
		}
		else {
			msg="실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
}
