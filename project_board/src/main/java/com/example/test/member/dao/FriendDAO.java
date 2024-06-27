package com.example.test.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FriendDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
//	public List<UserDTO> getAllUsers() {
//		// TODO Auto-generated method stub
//		return sqlSession.selectList("admin.getAllUsers");
//	}

}
