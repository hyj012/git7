package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Food;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.FoodRepository;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;




@Controller
@RequiredArgsConstructor
@Log
public class TestController {
	
	//객체생성
	private final MemberRepository memberRepository;
	
	//가상주소 http://localhost:8080/test
	@GetMapping("/test")
	public String test() {
		// templates 폴더  test.html 연결
		return "test";
	}
	
	//가상주소 http://localhost:8080/test2
	// => templates 폴더  test2.html 연결
	@GetMapping("/test2")
	public String test2() {
		// templates 폴더  test2.html 연결
		return "test2";
	}
	
	//가상주소 http://localhost:8080/test3
	// => templates 폴더  test3.jsp 연결
	@GetMapping("/test3")
	public String test3() {
		// templates 폴더  test3.jsp 연결
		return "test3";
	}
	
	//가상주소 http://localhost:8080/test4
	// => templates 폴더  test4.html 연결
	@GetMapping("/test4")
	public String test4(Model model) {
		// MemberDTO 데이터를 담아서 이동
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("kim");
		memberDTO.setPass("p123");
		memberDTO.setName("김길동");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		model.addAttribute("memberDTO", memberDTO);
		
		// templates 폴더  test4.html 연결
		return "test4";
	}
	
	//가상주소 http://localhost:8080/test5
	// => model  "memberList", memberList 담아서 이동
	// => templates 폴더  test5.html 연결
	@GetMapping("/test5")
	public String test5(Model model) {
		List<MemberDTO> memberList = new ArrayList<>();
		
		// MemberDTO 데이터를 담아서 이동
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("kim");
		memberDTO.setPass("p123");
		memberDTO.setName("김길동");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO2 = new MemberDTO();
		memberDTO2.setId("lee");
		memberDTO2.setPass("p456");
		memberDTO2.setName("이길동");
		memberDTO2.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO3 = new MemberDTO();
		memberDTO3.setId("hong");
		memberDTO3.setPass("p789");
		memberDTO3.setName("홍길동");
		memberDTO3.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberList.add(memberDTO);
		memberList.add(memberDTO2);
		memberList.add(memberDTO3);
		
		model.addAttribute("memberList", memberList);
		
		// templates 폴더  test5.html 연결
		return "test5";
	}
	
	//가상주소 http://localhost:8080/test6?id=kim&pass=p123
	@GetMapping("/test6")
	public String test6(Model model,MemberDTO memberDTO) {
		// @Param("id") String id, @Param("pass") String pass
		
		model.addAttribute("memberDTO", memberDTO);
		
		// templates 폴더  test6.html 연결
		return "test6";
	}
	
	//가상주소 http://localhost:8080/test7
	@GetMapping("/test7")
	public String test7(Model model) {
		log.info("test7 save()");
		// Member 데이터를 담아서 => 디비에 저장
		Member member = new Member();
		member.setId("kim");
		member.setPass("p123");
		member.setName("김길동");
		member.setDate(new Timestamp(System.currentTimeMillis()));
		
		//회원가입
		memberRepository.save(member);
		
		// templates 폴더  test.html 연결
		return "test";
	}
	


}
