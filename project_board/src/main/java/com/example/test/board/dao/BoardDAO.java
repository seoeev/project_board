package com.example.test.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.board.dto.BoardDTO;


@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;

	public void insertBoard(BoardDTO board) {
		sqlSession.insert("board.insert", board);
	}

	public BoardDTO findById(Integer board_id) {
		return sqlSession.selectOne("board.getBoard", board_id);
	}
	
//	public List<BoardDTO> findAll() {
//		return sqlSession.selectList("admin.getAllUsers");
//	}

}
