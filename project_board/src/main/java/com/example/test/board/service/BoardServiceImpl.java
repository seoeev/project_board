package com.example.test.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.board.dao.BoardDAO;
import com.example.test.board.dto.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDAO;

	@Override
	public void insertBoard(BoardDTO board) {
		boardDAO.insertBoard(board);
	}
	
	@Override
	public List<BoardDTO> findAllById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO findById(Integer board_id) {
		return boardDAO.findById(board_id);
	}


}
