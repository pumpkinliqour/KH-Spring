package com.kh.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller
public class AjaxTest {
	@Autowired
	BoardService serviceB;
	@Autowired
	MemberService serviceM;
		
	@RequestMapping("/ajaxTest1.do")
	@ResponseBody
	public List ajaxTest1() { 
		List list=serviceB.boardList(1, 5);
		
		return list;
	}
	
	@RequestMapping("/ajaxTest2.do")
	@ResponseBody
	public Member ajaxTest2(String userId) {
		Member m=serviceM.selectMemberOne(userId);
		return m;
	}
	
	@RequestMapping("/ajaxTest3.do")
	@ResponseBody
	public Map ajaxTest3(int no) {
		Map map=serviceB.selectBoard(no);
		return map;
	}
}
