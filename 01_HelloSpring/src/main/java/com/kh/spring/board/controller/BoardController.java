package com.kh.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.PageFactory;


@Controller
public class BoardController {
	private Logger logger=Logger.getLogger(BoardController.class); //로거라는 객체가 하나 생성이 됨
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(ModelAndView mv, @RequestParam(value="cPage", required=false, defaultValue="0") int cPage) {
		
		int numPerPage=5;
		
		int count=service.boardCount();
		
		List<Board> list=service.boardList(cPage, numPerPage);
		
		mv.addObject("pageBar", PageFactory.getPageBar(count, cPage, numPerPage, "/spring/board/boardList"));
		mv.addObject("count", count);
		mv.addObject("list", list);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@RequestMapping("/board/boardForm.do")
	public String boardForm() {
		
		return "board/boardForm";
	}
	
	@RequestMapping("board/boardFormEnd.do")
	public String boardFormEnd(HttpServletRequest request, String boardTitle, String boardWriter, String boardContent, MultipartFile[] upFile)//이렇게 받아올때는 무조건 값이 있어야함. view단에서 requried 해줘야함. 파일이 2개 이상일경우 배열로 받아야와야함
	{
		//받아야할 것들
		//board에 대한 값 title, comment.....
		//file 2개를 받아야됨.
		
		Map<String,String> board=new HashMap<String, String>(); //보드에 관한것들
		board.put("title", boardTitle);
		board.put("writer", boardWriter);
		board.put("content", boardContent);
		
		ArrayList<Attachment> files=new ArrayList<Attachment>(); //첨부파일에 관한것들
		
		//저장경로
		String saveDir=request.getSession().getServletContext().getRealPath("/resources/upload/board");
				
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) { //파일이 비어있지 않을때
				//파일명을 생성(rename)
				String oriFileName=f.getOriginalFilename();
				String ext=oriFileName.substring(oriFileName.lastIndexOf(".")); //확장자까지
				//rename규칙을 설정
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int randomV=(int)(Math.random()*1000);
				String reName=sdf.format(System.currentTimeMillis())+"_"+randomV+ext;
				try {
					f.transferTo(new File(saveDir+"/"+reName));
				}
				catch(IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				Attachment att=new Attachment();
				att.setReNamedFileName(reName);
				att.setOriginalFileName(oriFileName);
				files.add(att);
			}
		}
		int result=service.insertBoard(board, files);
		
		String msg="";
		String loc="/board/boardList.do";
		
		if(result>0) {
			msg="성공";
		}
		else {
			msg="실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		
		return "common/msg";
	}
	
	@RequestMapping("/board/boardView.do")
	public ModelAndView boardView(int boardNo)
	{
		ModelAndView mv=new ModelAndView();
		Map<String,String> map=service.selectBoard(boardNo);
		List<Map<String,String>> attach=service.selectAttachList(boardNo);
		
		mv.addObject("board", map);
		mv.addObject("attach", attach);
		mv.setViewName("board/boardView");
		
		return mv;
	}
	
	@RequestMapping("/board/fileDownLoad.do")
	public void fileDownLoad(String oName, String rName, HttpServletRequest request, HttpServletResponse response) {
		BufferedInputStream bis=null;
		ServletOutputStream sos=null;
		String dir=request.getSession().getServletContext().getRealPath("/resources/upload/board");
		File savedFile=new File(dir+"/"+rName); //경로
		try {
			FileInputStream fis=new FileInputStream(savedFile);
			bis=new BufferedInputStream(fis);
			sos=response.getOutputStream();
			String resFileName=""; //파일명처리하기 (인코딩)
			boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1||request.getHeader("user-agent").indexOf("Trident")!=-1;
			if(isMSIE) {
				resFileName=URLEncoder.encode(oName, "UTF-8");
				resFileName=resFileName.replaceAll("\\+", "%20");
			}
			else {
				resFileName=new String(oName.getBytes("UTF-8"), "ISO-8859-1"); //이렇게 해야 한글이 안깨짐
			}
			response.setContentType("application/octet-stream;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;fileName=\""+resFileName+"\"");
			response.setContentLength((int)savedFile.length()); //파일길이설정
			
			int read=0;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			sos.close();
			bis.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
}
