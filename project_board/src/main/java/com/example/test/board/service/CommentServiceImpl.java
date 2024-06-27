package com.example.test.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.board.dao.CommentDAO;
import com.example.test.board.dto.CommentDTO;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDAO commentDAO;

	@Override
	public List<CommentDTO> findAllById(Integer board_id) {
		return commentDAO.findAllById(board_id);
	}

	@Override
	public void insertComment(CommentDTO comment) {
		commentDAO.insertComment(comment);
	}

}
