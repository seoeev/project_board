package com.example.test.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.member.dao.FriendDAO;



@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDAO friendDAO;

}
