package com.example.test.board.service;

import java.util.List;

import com.example.test.board.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> findAllById();

	void insertBoard(BoardDTO board);

	BoardDTO findById(Integer board_id);

}
