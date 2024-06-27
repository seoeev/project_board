package com.example.test.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.board.dao.BoardDAO;
import com.example.test.board.dto.BoardDTO;
import com.example.test.board.dto.PageDTO;
import com.example.test.member.dao.MemberDAO;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDAO;

	@Autowired
	MemberDAO memberDAO;

	@Override
	public void insertBoard(BoardDTO board) {
		boardDAO.insertBoard(board);
	}
	
	@Override
	public BoardDTO findById(Integer board_id) {
		BoardDTO board = boardDAO.findById(board_id);
		board.setMember(memberDAO.getUserInfo(board.getUser_id()));
		return board;
	}

	@Override
	public List<BoardDTO> getBoardList(Integer page, Integer size) {
		Integer offset = (page - 1) * size;

		PageDTO pageDTO = new PageDTO();
		pageDTO.setOffset(offset);
		pageDTO.setSize(size);
		
		List<BoardDTO> boardList = boardDAO.getBoardList(pageDTO);
		
		for (BoardDTO board : boardList) {
			board.setMember(memberDAO.getUserInfo(board.getUser_id()));
		}
		
		return boardList;
	}

	@Override
	public void updateBoard(BoardDTO board) {
		boardDAO.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer board_id) {
		boardDAO.deleteBoard(board_id);
	}


	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}


}
