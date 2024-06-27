package com.example.test.board.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.board.dto.BoardDTO;
import com.example.test.board.dto.CommentDTO;
import com.example.test.board.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("comment")
@Controller
public class CommentController {

	@Autowired
	CommentService commetnService;
	
	
	// 댓글 등록
	@PostMapping("/insert/{board_id}")
	public String insertComment(HttpSession session, @PathVariable("board_id") Integer board_id, @RequestParam(value="content") String content) {

		CommentDTO comment = new CommentDTO();
		comment.setUser_id((Integer) session.getAttribute("user_id"));
		comment.setContent(content);
		comment.setBoard_id(board_id);
		comment.setCreate_date(LocalDateTime.now());
		
		commetnService.insertComment(comment);
		
		return "redirect:/board/detail/"+board_id;
	}
	
}
