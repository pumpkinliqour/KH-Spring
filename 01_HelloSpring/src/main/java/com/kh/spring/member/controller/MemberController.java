package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("userId") //세션 등록

@Controller
public class MemberController {
	
	private Logger logger=Logger.getLogger(MemberController.class); //로거라는 객체가 하나 생성이 됨
	
	@Autowired
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@RequestMapping("/member/memberLogin.do")
//	public String login(HttpSession session, String userId, String password, Model model) /*모델=공용객체를 담는것*/
	public ModelAndView login(HttpSession session, String userId, String password, Model model) /*모델=공용객체를 담는것*/
	{
		//모델과 뷰를 한번에 저장할 수 있는 객체
		ModelAndView mv=new ModelAndView();
		
		logger.debug("로그인 파라미터 userId : "+userId+" / password : "+password);
		
		//멤버변수 보낼 수 있는 방법
		//1. vo객체를 이용
		//2. map을 이용
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("password", password);
		
		Map<String,String> result=service.login(map);
		
		logger.debug("로그인 결과 : "+result);
		
		
		String msg="";
		String loc="/";
		
		if(result!=null) {
			//맵으로 받아오는 데이터는 모두 대문자임.
			//암호화한 패스워드를 서로 비교(equals)
			if(pwEncoder.matches(password, result.get("PASSWORD"))) {
			msg="로그인 성공";
			/*session.setAttribute("userId", result.get("USERID"));*/
//			model.addAttribute("userId", result.get("USERID")); //세션 등록
			mv.addObject("userId", result.get("USERID")); //세션 등록
			}
			else {
				msg="패스워드가 일치하지 않습니다";
			}
		}
		else {
			msg="아이디가 존재하지 않습니다.";
		}
		//모델객체 보내기
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		
		//뷰객체 보내기
		mv.setViewName("common/msg");
		
//		if(result!=null) {
//			try {
//				throw new NullPointerException();
//			}
//			catch(Exception e)
//			{
//				logger.error("내가 만든 에러 : "+e.getMessage());
//				throw new NullPointerException();
//			}
//		}
		
//		return "common/msg";
		return mv;
	}
	
	@RequestMapping("/member/memberLogout.do")
	public String logout(SessionStatus status, Model model) {
		//SessionAttributes 이용 로그인이면 : set Complete() 메소드 이용해서 로그아웃 처리
		//HttpSession 이용 로그인이면 : invalide() 메소드 이용해서 로그아웃 처리
		
//		if(!status.isComplete()) {
//			status.setComplete();
//		}
		
		String msg="로그아웃 되었습니다";
		String loc="/";
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		status.setComplete();
		
		return "common/msg";
	}
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		
		
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Model model, Member member) {
		
		//회원가입시에 비밀번호를 DB에 저장 DB비밀번호를 저장할때는 개인정보 보호법에 의해 암호화처리를 반드시 해줘야함
		//스프링에서 인증 권한을 부여하는 모듈을 제공하는 모듈을 이용해야함(SpringSecurity => 암호화하는 것으로 이용)
		//암호화 방법
		//1.ShaPasswordEncoder() (4.1이후부터는 안됨)
		//2.BcryptPasswordEncoder() (사용권유)
		//환경설정이 다 끝났다면 컨트롤러에서 설정
		//@Autowired BCrypotPasswordEncoder
		
		String rawPw=member.getPassword(); //멤버의 패스워드 가져오기
		member.setPassword(pwEncoder.encode(rawPw)); //암호화 처리
		
		int result=service.memberEnrollEnd(member);
		String msg="";
		String loc="";
		if(result>0) {
			msg="회원가입이 완료되었습니다.";
		}
		else {
			msg="회원가입이 실패하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
	
	@RequestMapping("/member/myPage.do")
	public ModelAndView myPage(String userId) {
		ModelAndView mv=new ModelAndView();
		
		System.out.println(userId);
		Member member=service.selectMemberOne(userId);
		
		mv.addObject("member", member);
		mv.setViewName("member/myPage");
		
		return mv;
	}
	
	@RequestMapping("/member/memberUpdate.do")
	public ModelAndView myPageUpdate(ModelAndView mv, Member member) {
		mv=new ModelAndView();
		String rawPw=member.getPassword();
		member.setPassword(pwEncoder.encode(rawPw));
		
		System.out.println(member);
		
		int result=service.myPageUpdate(member);
		
		String msg="";
		String loc="";
		
		if(result>0) {
			msg="정보변경이 완료되었습니다.";
		}
		else {
			msg="정보변경에 실패하였습니다.";
		}
		
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		mv.setViewName("common/msg");
		
		return mv;
	}
}
