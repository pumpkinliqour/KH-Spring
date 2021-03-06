package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> boardList(int cPage, int numPerPage);
	int boardCount();
	int insertBoard(Map<String,String> board);
	int insertAttach(Attachment a);
	Map<String,String> selectBoard(int boardNo);
	List<Map<String,String>> selectAttachList(int boardNo);
}
