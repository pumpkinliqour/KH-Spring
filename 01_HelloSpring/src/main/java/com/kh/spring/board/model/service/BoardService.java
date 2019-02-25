package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardService {
	
	List<Board> boardList(int cPage, int numPerPage);
	int boardCount();
	int insertBoard(Map<String, String> board, List<Attachment> files);
}
