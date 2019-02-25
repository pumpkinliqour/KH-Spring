package com.kh.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int insertMemo(Map<String,String> map) {
		return session.insert("memo.insertMemo", map);
	}
	
	@Override
	public List<Memo> selectMemoList(){
		return session.selectList("memo.memoList");
	}
	
	@Override
	public int deleteMemo(int memoNo){
		return session.delete("memo.deleteMemo",memoNo);
	}
}
