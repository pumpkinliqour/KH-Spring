package com.kh.spring.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public Map<String,String> login(Map<String, String> map) {
		
		return sqlSession.selectOne("member.login", map);
	}

	@Override
	public int memberEnrollEnd(Member member) {
		return sqlSession.insert("member.memberEnroll", member);
	}
	
	@Override
	public int myPageUpdate(Member member) {
		return sqlSession.update("member.myPageUpdate", member);
	}
	
	@Override
	public Member selectMemberOne(String userId) {
		return sqlSession.selectOne("member.selectMemberOne", userId);
	}

	@Override
	public int checkId(String userId) {
		return sqlSession.selectOne("member.selectUserId", userId);
	}
	
	
}
