package com.example.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.test.board.dto.BoardDTO;
import com.example.test.board.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String showMain(Model model, HttpSession session) {
	
		List<BoardDTO> boardList = boardService.findAllById();
		
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("nickname", session.getAttribute("nickname"));
		
		return "index";
	}
	
	
}
