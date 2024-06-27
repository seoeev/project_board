package com.example.test.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.board.dto.BoardDTO;
import com.example.test.board.dto.CommentDTO;
import com.example.test.board.service.BoardService;
import com.example.test.board.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("board")
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	
	@GetMapping("")
	public String showBoardFrom(Model model) {
		BoardDTO board = new BoardDTO();
		model.addAttribute("board", board);
		return "board_from";
	}
	
	// 게시물 등록
	@RequestMapping(value="/insertBoard" ,method = RequestMethod.POST)
	@ResponseBody
	public Integer insertBoard(HttpSession session, @RequestParam(value="title") String title, 
												@RequestParam(value="content") String content, 
												@RequestParam(value="is_friend_only") Character is_friend_only) {

		BoardDTO board = new BoardDTO();
		board.setUser_id((Integer) session.getAttribute("user_id"));
		board.setTitle(title);
		board.setContent(content);
		board.setCreate_date(LocalDateTime.now());
		board.setIs_friend_only(is_friend_only);
		
		boardService.insertBoard(board);
		return board.getBoard_id();
	}
	

	// 게시물 상세 페이지
	@GetMapping("/detail/{board_id}")
	public String boardDtail(HttpSession session, Model model, @PathVariable("board_id") Integer board_id) {
		
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("nickname", session.getAttribute("nickname"));
				
		// 게시글 정보 불러오기
		BoardDTO board = boardService.findById(board_id);
		model.addAttribute("board", board);
		
		List<CommentDTO> commentList = commentService.findAllById(board_id);
		model.addAttribute("commentList", commentList);
		
		return "board_detail";
	}
	
	
	// 게시물 상세 페이지
	@GetMapping("/modify/{user_id}/{board_id}")
	public String modifyBoard(Model model, @PathVariable("user_id") Integer user_id, @PathVariable("board_id") Integer board_id) {						
		// 게시글 정보 불러오기
		BoardDTO board = boardService.findById(board_id);
		model.addAttribute("board", board);
		
		return "board_from";
	}
	
	
	// 게시물 수정
	@RequestMapping(value="/updateBoard" ,method = RequestMethod.POST)
	@ResponseBody
	public Integer updateBoard(HttpSession session, @RequestParam(value="title") String title, 
												@RequestParam(value="content") String content, 
												@RequestParam(value="is_friend_only") Character is_friend_only,
												@RequestParam(value="board_id") Integer board_id) {

		BoardDTO board = new BoardDTO();
		board.setBoard_id(board_id);
		board.setUser_id((Integer) session.getAttribute("user_id"));
		board.setTitle(title);
		board.setContent(content);
		board.setIs_friend_only(is_friend_only);
		
		boardService.updateBoard(board);
		return board.getBoard_id();
	}
	
	@GetMapping("/delete/{board_id}")
	public String deleteBoard(@PathVariable("board_id") Integer board_id) {						
		boardService.deleteBoard(board_id);
		
		return "redirect:/";
	}
	
	
}
