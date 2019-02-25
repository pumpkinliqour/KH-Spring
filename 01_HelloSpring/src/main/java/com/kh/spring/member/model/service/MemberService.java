package com.kh.spring.member.model.service;

import java.util.Map;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Map<String,String> login(Map<String, String> map);
	int memberEnrollEnd(Member member);
	int myPageUpdate(Member member);
	Member selectMemberOne(String userId);
	int checkId(String userId);
}
