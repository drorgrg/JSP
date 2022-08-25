package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{
	
//	객체생성 부모 = 자식
	@Inject
	private BoardDAO boardDAO;
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		//폼에서 가져온값 name pass subject content
		// num,readcount,date
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		// max(num)+1
		if(boardDAO.getMaxNum()==null) {
			//게시판 글이 없음
			boardDTO.setNum(1);
		} else {
			boardDTO.setNum(boardDAO.getMaxNum() + 1);
		}
		
		
		boardDAO.insertBoard(boardDTO);
		
	}
	@Override
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		// pageSize pageNum currentPage
		int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1;
		int endRow = startRow + pageDTO.getPageSize()-1;
		// sql => limit #{startRow-1},#{pageSize}
		pageDTO.setStartRow(startRow-1);
		pageDTO.setEndRow(endRow);
		
		return boardDAO.getBoardList(pageDTO);
	}
	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}
	@Override
	public BoardDTO getBoard(int num) {
		return boardDAO.getBoard(num);
	}
	@Override
	public BoardDTO numCheck(BoardDTO boardDTO) {
		return boardDAO.numCheck(boardDTO);
	}
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
	}

}
