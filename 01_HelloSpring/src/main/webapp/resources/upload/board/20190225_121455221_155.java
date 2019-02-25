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
import com.kh.spring.common.exception.BoardException;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int insertBoard(Map<String, String> board, List<Attachment> files) 
	throws BoardException
	{
		//dao세번가야함.
		int result=0;
		int boardNo=0;
		try {
			result=dao.insertBoard(board);
			System.out.println("돌아온값 : "+board.get("boardNo"));	
			if(result==0)
			{
				throw new BoardException();
			}
			for(Attachment a : files)
			{
				a.setBoardNo(Integer.parseInt(board.get("boardNo")));
				result=dao.insertAttach(a);
				/*if(result==0) throw new BoardException();*/
			}
			
		}catch(Exception e)
		{		
			throw new RuntimeException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Map<String, String>> selectBoardList(int cPage, int numPerPage) {
		return dao.selectBoardList(cPage, numPerPage);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount();
	}

	
	
}




