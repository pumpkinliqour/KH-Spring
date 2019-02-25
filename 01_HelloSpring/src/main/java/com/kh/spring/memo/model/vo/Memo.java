package com.kh.spring.memo.model.vo;

import java.sql.Date;

public class Memo {
	private int memoNo;
	private String memo;
	private String password;
	private Date memoDate;
	
	public Memo() {
		// TODO Auto-generated constructor stub
	}

	public Memo(int memoNo, String memo, String password, Date memoDate) {
		super();
		this.memoNo = memoNo;
		this.memo = memo;
		this.password = password;
		this.memoDate = memoDate;
	}

	public int getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getMemoDate() {
		return memoDate;
	}

	public void setMemoDate(Date memoDate) {
		this.memoDate = memoDate;
	}

	@Override
	public String toString() {
		return "Memo [memoNo=" + memoNo + ", memo=" + memo + ", password=" + password + ", memoDate=" + memoDate + "]";
	}
}

