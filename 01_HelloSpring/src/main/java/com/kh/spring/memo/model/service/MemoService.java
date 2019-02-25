package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoService {

	int insertMemo(Map<String, String> map);
	List<Memo> selectMemoList();
	int deleteMemo(int memoNo);
}
