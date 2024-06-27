package com.example.test.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.member.dao.MemberDAO;
import com.example.test.member.dto.MemberDTO;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO memberDAO;

	@Override
	public MemberDTO signin(MemberDTO user) {
		return memberDAO.signin(user);
	}

	@Override
	public int emailCheck(String email) {
		return memberDAO.emailCheck(email);
	}

	@Override
	public void insert(MemberDTO user) {
		user.setLeave_status('N');	// 탈퇴여부 설정 (N 미탈퇴, Y 탈퇴)
		memberDAO.insert(user);	
	}

	@Override
	public int nicknameCheck(String nickname) {
		return memberDAO.nickname_Check(nickname);
	}

}
