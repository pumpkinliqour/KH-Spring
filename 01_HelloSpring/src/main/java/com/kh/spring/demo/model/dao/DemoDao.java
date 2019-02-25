package com.kh.spring.demo.model.dao;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {

	int insertDev(Dev dev);
	List<Dev> selectDemoList();
	Dev selectOne(int on);
	int updateDev(Dev dev);
	int deleteDev(int no);
}

