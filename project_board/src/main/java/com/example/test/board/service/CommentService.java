package com.example.test.board.service;

import java.util.List;

import com.example.test.board.dto.CommentDTO;

public interface CommentService {

	List<CommentDTO> findAllById(Integer board_id);

	void insertComment(CommentDTO comment);

}
