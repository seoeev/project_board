package com.example.test.board.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private Integer board_id;
    private String title;
    private String content;
    private LocalDateTime create_date;
    private Integer user_id;
	
}
