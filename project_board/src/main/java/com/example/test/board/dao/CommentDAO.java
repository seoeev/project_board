package com.example.test.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.board.dto.CommentDTO;


@Repository
public class CommentDAO {
	@Autowired
	SqlSessionTemplate sqlSession;

	public List<CommentDTO> findAllById(Integer board_id) {
		return sqlSession.selectList("comment.getAllComment", board_id);
	}

	public void insertComment(CommentDTO comment) {
		sqlSession.insert("comment.insert", comment);
	}
	

}
