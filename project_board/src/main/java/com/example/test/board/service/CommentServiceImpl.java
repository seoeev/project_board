package com.example.test.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.board.dao.CommentDAO;
import com.example.test.board.dto.CommentDTO;
import com.example.test.member.dao.MemberDAO;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDAO commentDAO;

	@Autowired
	MemberDAO memberDAO;
	
	
	@Override
	public List<CommentDTO> findAllById(Integer board_id) {
		List<CommentDTO> commentList = commentDAO.findAllById(board_id);
		
		for (CommentDTO comment : commentList) {
			comment.setMember(memberDAO.getUserInfo(comment.getUser_id()));
		}
		
		return commentList;
	}

	@Override
	public void insertComment(CommentDTO comment) {
		commentDAO.insertComment(comment);
	}

}
