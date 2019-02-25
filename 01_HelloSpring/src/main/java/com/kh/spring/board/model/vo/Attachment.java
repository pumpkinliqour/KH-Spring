package com.kh.spring.board.model.vo;

import java.sql.Date;

public class Attachment {
	private int attachmentNo;
	private int boardNo;
	private String originalFileName;
	private String reNamedFileName;
	private Date uploadDate;
	private int downloadCount;
	private String status;
	
	public Attachment() {
		// TODO Auto-generated constructor stub
	}

	public Attachment(int attachmentNo, int boardNo, String originalFileName, String reNamedFileName, Date uploadDate,
			int downloadCount, String status) {
		super();
		this.attachmentNo = attachmentNo;
		this.boardNo = boardNo;
		this.originalFileName = originalFileName;
		this.reNamedFileName = reNamedFileName;
		this.uploadDate = uploadDate;
		this.downloadCount = downloadCount;
		this.status = status;
	}

	public int getAttachmentNo() {
		return attachmentNo;
	}

	public void setAttachmentNo(int attachmentNo) {
		this.attachmentNo = attachmentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getReNamedFileName() {
		return reNamedFileName;
	}

	public void setReNamedFileName(String reNamedFileName) {
		this.reNamedFileName = reNamedFileName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentNo=" + attachmentNo + ", boardNo=" + boardNo + ", originalFileName="
				+ originalFileName + ", reNamedFileName=" + reNamedFileName + ", uploadDate=" + uploadDate
				+ ", downloadCount=" + downloadCount + ", status=" + status + "]";
	}
	
}
