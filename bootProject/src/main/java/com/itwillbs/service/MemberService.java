package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.entity.Member;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class MemberService {
	//MemberRepository 객체생성
	private final MemberRepository memberRepository;
	
	//PasswordEncoder 객체생성
	private final PasswordEncoder passwordEncoder;
	
	public void insertMember(Member member2) {
		log.info("MemberService insertMember()");
		
//		member.setDate(new Timestamp(System.currentTimeMillis()));
		
		// 비밀번호 암호화, 권한 부여
		Member member = Member
		.createUser(member2.getId(), member2.getPass(), passwordEncoder, 
				member2.getName());
		
		// save 메서드 호출
		memberRepository.save(member);
	}

	public Member findByIdAndPass(Member member) throws Exception{
		log.info("MemberService findByIdAndPass()");
		
//		String pass = passwordEncoder.encode(member.getPass());
//		log.info(pass);
		
		Member member2 = memberRepository.findById(member.getId())
				.orElseThrow(()-> new Exception("회원 없음"));
		log.info(member2.toString());
		
//		passwordEncoder.matches(폼에서 입력한 비밀번호, 암호화된 디비에 저장된 비밀번호);
		boolean match = passwordEncoder.matches(member.getPass(), member2.getPass());
		System.out.println("passwordEncoder.matches : " + match);
		
		if(match == true) {
			//비밀번호 일치
			return member2;
		}else {
			//비밀번호 틀림 
			return null;
		}
		
//		return memberRepository.findByIdAndPass(member.getId(), member.getPass());
		
	}

	public Optional<Member> findById(String id) {
		log.info("MemberService findByIdAfindByIdndPass()");
		
		return memberRepository.findById(id);
	}

	public void updateMember(Member member) {
		log.info("MemberService updateMember()");
		// id 있으면 => update, id 없으면 => insert
		// update members set pass=?, name=?,date =? where id=?
		memberRepository.save(member);
	}

	public void deleteMember(Member member) {
		log.info("MemberService deleteMember()");
	
		memberRepository.deleteById(member.getId());
	}

	public List<Member> getMemberList() {
		log.info("MemberService getMemberList()");
		
		return memberRepository.findAll();
	}

}
