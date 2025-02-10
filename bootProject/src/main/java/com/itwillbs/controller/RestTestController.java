package com.itwillbs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestTestController {
	
	//가상주소 http://localhost:8080/rTest
	@GetMapping("/rTest")
	public String rTest() {
		return "Hello, Spring Boot";
	}
	
	//가상주소 http://localhost:8080/rTest2 
	// => 결과 "rTest2 content"
	@GetMapping("/rTest2")
	public String rTest2() {
		return "rTest2 content";
	}
	
	//가상주소 http://localhost:8080/rTest3
	// => 결과 MemberDTO 
	@GetMapping("/rTest3")
	public MemberDTO rTest3() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("kim");
		memberDTO.setPass("p123");
		memberDTO.setName("김길동");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		return memberDTO;
	}
	
	//가상주소 http://localhost:8080/rTest4
	// => 결과 List<MemberDTO> 
	@GetMapping("/rTest4")
	public List<MemberDTO> rTest4() {
		
		List<MemberDTO> memberList = new ArrayList<>();
		
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
		
		return memberList;
	}

}
