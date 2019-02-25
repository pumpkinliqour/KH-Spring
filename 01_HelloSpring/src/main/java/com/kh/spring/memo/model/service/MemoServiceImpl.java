package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	MemoDao dao;
	
	@Override
	public int insertMemo(Map<String,String> map) {
		
		return dao.insertMemo(map);
	}
	
	@Override
	public List<Memo> selectMemoList(){
		return dao.selectMemoList();
	}
	
	@Override
	public int deleteMemo(int memoNo) {
		return dao.deleteMemo(memoNo);
	}
}
