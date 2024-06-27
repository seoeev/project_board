package com.example.test.board.service;

import java.util.List;

import com.example.test.board.dto.BoardDTO;

public interface BoardService {

	void insertBoard(BoardDTO board);

	BoardDTO findById(Integer board_id);

	List<BoardDTO> getBoardList(Integer page, Integer size);

	void updateBoard(BoardDTO board);

	void deleteBoard(Integer board_id);

	int getBoardCount();

}
