package com.example.test.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.member.dto.MemberDTO;
import com.example.test.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired 
	HttpSession session;

	// === 로그인 === //
	
	// 로그인 페이지 띄움
	@GetMapping("/signin")
	public String signinform() {
		Integer user_id = (Integer) session.getAttribute("user_id");		
		
		// 로그인 되어 있으면 홈으로
		if(user_id != null)
			return "redirect:/";
		
		return "sign_in";
	}
	
	// 로그인 결과처리
	@PostMapping("/signin/result")
	public String signin_result(Model model, @RequestParam("user_id") String user_id, 
									@RequestParam("password") String password, HttpSession session) {
		MemberDTO user = new MemberDTO();

		try {
			user.setEmail(user_id);
			user.setPassword(password);
			
			user = memberService.signin(user);
			
			if(user != null) {
				// 로그인 성공

				session.setAttribute("user_id", user.getUser_id());
				session.setAttribute("nickname", user.getNickname());
	
				return "redirect:/";
			}else {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "/sign_in";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
		
		return "redirect:/signin";
	}
	
	
	@RequestMapping("/signout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션클리어
		return "redirect:/signin";
	}

	// === 로그인 끝 === //

	
	// === 회원가입 === //
	
	@GetMapping("signup")
	public String showSignup() {
		return "sign_up";
	}


	// 유저 데이터 insert 처리 
	@PostMapping("user/insert")
	public String insertUser(@ModelAttribute MemberDTO user) {
		// 유저 데이터 insert
		memberService.insert(user);

		return "/sign_in";
	}

	
	// 이메일 중복 검사
	@RequestMapping(value="/emailCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam(value="email")  String email) {
		String chk = "";
		int result = 0;
		
		result = memberService.emailCheck(email);
		
		if(result > 0) {
			chk = "redundancy"; 	// 아이디 중복
		} else if(result == 0){
			chk = "noredundancy";	// 아이디 중복 아님
		}
		return chk;
	}
	
	// 닉네임 중복 검사
	@RequestMapping(value="/nicknameCheck" ,method = RequestMethod.POST)
	@ResponseBody
	public String nicknameCheck(@RequestParam(value="nickname")  String nickname) {
		String chk = "";
		int result = 0;
		
		result = memberService.nicknameCheck(nickname);
		
		if(result > 0) {
			chk = "redundancy"; 	// 중복
		} else if(result == 0){
			chk = "noredundancy";	// 중복 아님
		}
		return chk;
	}
	
	// === 회원가입 끝 === //
	
}
