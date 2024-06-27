package com.example.test.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.member.dto.MemberDTO;


@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate sqlSession;

	// 회원가입_유저정보 추가
	public void insert(MemberDTO user) {
		sqlSession.insert("member.insert", user);
		
	}

	// 회원가입_이메일 체크
	public int emailCheck(String email) {
		return sqlSession.selectOne("member.email_check", email);
	}
	
	// 회원가입_닉네임 체크
	public int nickname_Check(String nickname) {
		return sqlSession.selectOne("member.nickname_check", nickname);
	}
	
	// 로그인_로그인 후 이름 뱉어내기
	public MemberDTO signin(MemberDTO user) {
		return sqlSession.selectOne("member.signin", user);
	}


}
