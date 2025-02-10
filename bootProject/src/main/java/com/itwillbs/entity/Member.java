package com.itwillbs.entity;

import java.sql.Timestamp;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//엔티티 매핑 관련 어노테이션
//@Entity : 클래스 엔티티 선언
//@Table : 엔티티와 매핑할 테이블 지정
//@Id : 테이블에서 기본키 사용할 속성 지정
//@Column : 필드와 컬럼 매핑(멤버변수 테이블 열이름 매핑)
// => name="컬럼명", length=크기,nullable=false,unique(DDL),
//    columnDefinition = varchar(5)지접지정, insertable,updateable
//@GeneratedValue(strategy=GenerationType.AUTO)  키값 생성,자동으로 증가
//@Lob BLOB,CLOB 타입 매핑
//@CreateTimestamp insert시 시간 자동 저장
//@Enumerated  enum 타입매핑

@Entity
@Table(name = "members")
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	@Column(name = "id", length = 50)
	private String id;
	
//	비밀번호 암호화 => 데이터베이스 저장
	@Column(name="pass", nullable = false)
	private String pass;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private Timestamp date;
	
//	권한 컬럼 => 일반사용자 USER, 관리자 ADMIN
	@Column(name = "role")
	private String role;
	
//	스프링 시큐리티 : 애플리케이션의 인증 , 인가를 일관된 형태로 처리하는 모듈 
//  인증 : 로그인 사용자 식별 
//  인가 : 시스템 자원에 대한 접근을 통제	
	
//	SecurityFilterChain -> 인증 
//                      -> 인가	
	
//	생성자
	public Member() {
	}
	
	public Member(String id, String pass, String name, String roleUser, Timestamp date) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.role = roleUser;
		this.date = date;
	}
	
	public static Member createUser(String id,String pass,
			PasswordEncoder passwordEncoder, String name) {
		
		if(id.equals("admin")) {
			return new Member(id,passwordEncoder.encode(pass),name,
					"ADMIN",new Timestamp(System.currentTimeMillis()));
		}else {
			return new Member(id,passwordEncoder.encode(pass),name,
					"USER",new Timestamp(System.currentTimeMillis()));
		}
		
	}
	
	
	

}
