package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	SqlSessionTemplate session;

	@Override
	public List<Board> boardList(int cPage, int numPerPage) {
		RowBounds rows=new RowBounds((cPage-1)*numPerPage, numPerPage);
		return session.selectList("board.boardList", null, rows);
	}
	
	@Override
	public int insertBoard(Map<String, String> board) {
		return session.insert("board.insertBoard",board);
	}
	
	@Override
	public int insertAttach(Attachment a) {
		return session.insert("board.insertAttach", a);
	}
	
	@Override
	public int boardCount() {
		return session.selectOne("board.boardCount");
	}

	@Override
	public Map<String, String> selectBoard(int boardNo) {
		return session.selectOne("board.selectBoard",boardNo);
	}

	@Override
	public List<Map<String, String>> selectAttachList(int boardNo) {
		return session.selectList("board.selectAttach", boardNo);
	}

	
	
}
