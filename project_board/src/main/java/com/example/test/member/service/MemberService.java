package com.example.test.member.service;

import com.example.test.member.dto.MemberDTO;

public interface MemberService {

	MemberDTO signin(MemberDTO user);

	int emailCheck(String email);

	void insert(MemberDTO user);

	int nicknameCheck(String nickname);

}
