package com.example.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.board.dto.BoardDTO;
import com.example.test.board.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String showMain(Model model, HttpSession session,
						@RequestParam(name="page", defaultValue = "1") int page,
			            @RequestParam(name="size", defaultValue = "5") int size) {


		List<BoardDTO> boardList = boardService.getBoardList(page, size);

		int totalPosts = boardService.getBoardCount();

		int totalPages = (int) Math.ceil((double) totalPosts / size);
        
        
		model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("nickname", session.getAttribute("nickname"));
		
		return "index";
	}
	
	
}
