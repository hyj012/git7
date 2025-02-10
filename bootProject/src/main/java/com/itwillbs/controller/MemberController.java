package com.itwillbs.controller;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.entity.Member;
import com.itwillbs.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class MemberController {
	//객체생성
	private final MemberService memberService;
	
	//가상주소 http://localhost:8080/insert
	// => /member/insert.html 이동
	@GetMapping("/insert")
	public String insert() {
		log.info("MemberController insert()");
		return "/member/insert";
	}
	
	//가상주소 http://localhost:8080/insert  post방식
	// => 회원가입
	// => com.itwillbs.service.MemberService 파일 만들고 
	// => insertMember() 메서드 정의
	// => MemberService 객체생성 insertMember() 호출
	// => redirect:/login 이동
	@PostMapping("/insert")
	public String insertPost(Member member) {
		log.info("MemberController insertPost()");
		
		memberService.insertMember(member);
		
		return "redirect:/login";
	}
	
	//가상주소 http://localhost:8080/login
	// => /member/login.html 이동
	@GetMapping("/login")
	public String login() {
		log.info("MemberController login()");
		return "/member/login";
	}
	
	//가상주소 http://localhost:8080/login  post방식
	// => MemberService 객체생성 
	// => Member member2 = findByIdAndPass(member) 호출
	// => if member2 == null => redirect:/login 이동
	// => else member2 != null => session 값 생성 => redirect:/main 이동
//	@PostMapping("/loginPro")
//	public String loginPost(Member member, HttpSession session) {
//		log.info("MemberController loginPost()");
//		
//		Member member2 = memberService.findByIdAndPass(member);
//		
//		if(member2 == null) {
//			return "redirect:/login";
//		}else {
//			session.setAttribute("id", member.getId());
//			
//			return "redirect:/main";
//		}
//	}
	
	//가상주소 http://localhost:8080/main
	// => /member/main.html 이동
	@GetMapping("/main")
	public String main() {
		log.info("MemberController main()");
		// 스프링 시큐리티 로그인 정보
		log.info(SecurityContextHolder
				.getContext().getAuthentication()
				.getPrincipal().toString());
//		org.springframework.security.core.userdetails.User 
//		[Username=kim5, Password=[PROTECTED], Enabled=true,
//				AccountNonExpired=true, CredentialsNonExpired=true, 
//				AccountNonLocked=true, Granted Authorities=[ROLE_USER]]
		// 사용자 
		String id = SecurityContextHolder
				.getContext().getAuthentication().getName();
		log.info(id);
//		kim5
		
		// 권한 
		Collection<? extends GrantedAuthority> authorities 
		= SecurityContextHolder
		.getContext().getAuthentication().getAuthorities();
		
		Iterator<? extends GrantedAuthority> iter= authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		log.info(role);
//		ROLE_USER
		
		return "/member/main";
	}
	
	//가상주소 http://localhost:8080/logout
	// => 세션 초기화
	// => "redirect:/login" 이동
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		log.info("MemberController logout()");
//		
//		session.invalidate();
//		
//		return "redirect:/login";
//	}
	
	// 가상주소 http://localhost:8080/info
	// 세션값 가져오기
	// => Optional<Member> member2 = findById(id) 호출
	// => Optional Null값을 허용하는 Wrapper 클래스
	// => Member member = member2.get()
	// => model member 담아서 이동
	// => "/member/info" 이동
	@GetMapping("/info")
	public String info(HttpSession session, Model model) {
		log.info("MemberController info()");
		
//		String id = (String)session.getAttribute("id");
		
		// 사용자 
		String id = SecurityContextHolder
				.getContext().getAuthentication().getName();
		
		
		Optional<Member> member = memberService.findById(id);
		
		model.addAttribute("member", member.get());
		
		return "/member/info";
	}
	
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		log.info("MemberController update()");
//		String id = (String)session.getAttribute("id");
		
		// 사용자 
		String id = SecurityContextHolder
				.getContext().getAuthentication().getName();
		
		Optional<Member> member = memberService.findById(id);
		
		model.addAttribute("member", member.get());
		
		return "/member/update";
	}
	
	@PostMapping("/update")
	public String updatePost(Member member, HttpSession session) throws Exception{
		log.info("MemberController updatePost()");
		log.info(member.toString());
		
		Member member2 = memberService.findByIdAndPass(member);
		
		if(member2 == null) {
			return "redirect:/update";
		}else {
			// 수정  id,pass,name,날짜 null,role
			//날짜 추가
			member.setDate(member2.getDate());
			member.setPass(member2.getPass());
			member.setRole(member2.getRole());
			
			memberService.updateMember(member);
			
			return "redirect:/main";
		}
	}
	
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model) {
		log.info("MemberController delete()");
		
		return "/member/delete";
	}
	
	@PostMapping("/delete")
	public String deletePost(Member member, HttpSession session) throws Exception{
		log.info("MemberController deletePost()");
		
		Member member2 = memberService.findByIdAndPass(member);
		
		if(member2 == null) {
			return "redirect:/delete";
		}else {
			memberService.deleteMember(member);
			
//			session.invalidate();
			
//			return "redirect:/login";
			
			return "redirect:/logout";
		}
	}
	
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		log.info("MemberController list()");
		
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "/member/list";
	}
	
}
