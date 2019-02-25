package com.kh.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.demo.model.vo.Dev;

@Repository
public class DemoDaoImpl implements DemoDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	
	@Override
	public int updateDev(Dev dev) {
		return sqlSession.update("demo.updateDev",dev);
	}



	@Override
	public Dev selectOne(int on) {
		return sqlSession.selectOne("demo.selectOne",on);
	}



	@Override
	public List<Dev> selectDemoList() {
		return sqlSession.selectList("demo.selectList");
	}




	@Override
	public int insertDev(Dev dev) {
		// TODO Auto-generated method stub
		return sqlSession.insert("demo.insertDev",dev);
	}

	@Override
	public int deleteDev(int no) {
		return sqlSession.delete("demo.deleteDev", no);
	}
	
}







