package com.kh.spring.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public Map<String,String> login(Map<String, String> map) {
		return dao.login(map);
	}
	
	@Override
	public int memberEnrollEnd(Member member) {
		return dao.memberEnrollEnd(member);
	}
	
	@Override
	public int myPageUpdate(Member member) {
		return dao.myPageUpdate(member);
	}

	@Override
	public Member selectMemberOne(String userId) {
		return dao.selectMemberOne(userId);
	}
}
