package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.exception.BoardException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao dao;

	@Override
	public List<Board> boardList(int cPage, int numPerPage) {
		return dao.boardList(cPage, numPerPage);
	}

	@Override
	public int boardCount() {
		return dao.boardCount();
	}

	@Override
	public int insertBoard(Map<String, String> board, List<Attachment> files) {
		//dao를 세번 가야함.
		int result=0;
		try {
			result=dao.insertBoard(board);
			if(result==0) {
				throw new BoardException(); //롤백할 상황일 때
			}
			for(Attachment a : files) {
				a.setBoardNo(Integer.parseInt(board.get("boardNo")));
				result=dao.insertAttach(a);
				if(result==0) {
					throw new BoardException();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	

}
