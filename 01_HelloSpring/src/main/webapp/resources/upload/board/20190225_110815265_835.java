package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.common.exception.BoardException;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	@Override
	public int insertBoard(Map<String, String> board, List<Attachment> files) {
		//dao세번가야함.
		int result=0;
		int boardNo=0;
		try {
			result=dao.insertBoard(board);
					
/*			if(result==0)
			{
				throw new BoardException();
			}*/
		}catch(Exception e)
		{
			e.printStackTrace();
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




