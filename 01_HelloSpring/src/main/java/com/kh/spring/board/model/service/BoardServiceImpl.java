package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	/*@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=Exception.class)*/
	public int insertBoard(Map<String, String> board, List<Attachment> files) throws BoardException {
		//dao를 세번 가야함.
		int result=0;
		try {
			result=dao.insertBoard(board);
			if(result==0) {
				throw new BoardException(); //익셉션 출력
			}
			for(Attachment a : files) {
				a.setBoardNo(Integer.parseInt(board.get("boardNo")));
				result=dao.insertAttach(a);
				if(result==0) {
					throw new BoardException("파일 업로드 실패");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
		
		return result;
	}

	@Override
	public Map<String, String> selectBoard(int boardNo) {
		
		return dao.selectBoard(boardNo);
	}

	@Override
	public List<Map<String, String>> selectAttachList(int boardNo) {
		return dao.selectAttachList(boardNo);
	}
	
	

}
