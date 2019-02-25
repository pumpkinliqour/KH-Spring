package com.kh.spring.member.model.dao;

import java.util.Map;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	Map<String,String> login(Map<String, String> map);
	int memberEnrollEnd(Member member);
	int myPageUpdate(Member member);
	Member selectMemberOne(String userId);
	int checkId(String userId);
}
