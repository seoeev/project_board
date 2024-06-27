package com.example.test.board.dto;

import java.time.LocalDateTime;

import com.example.test.member.dto.MemberDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

	private Integer comment_id; 
    private String content;
    private LocalDateTime create_date;
    private Integer user_id;
	private Integer board_id;
	
	private MemberDTO member;
	
}
