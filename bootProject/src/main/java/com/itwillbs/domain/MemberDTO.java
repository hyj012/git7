package com.itwillbs.domain;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.extern.java.Log;


@ToString
@Getter
public class MemberDTO {
	
//	Lombok 라이브러리
//	@Getter/@Setter  : Getter , Setter 메서드 생성
//	@ToString : toString() 메서드 생성
//	@ToString(exclude= {"변수명"}) : 변수명 제외 toString() 메서드 생성
//	@NonNull : null 체크=> NullPointException 예외발생
//	@EqualsAndHashCode : equals() hashCode() 메서드 생성
//	@Builder : 빌더 패턴 이용 객체생성
//	@NoArgsConstructor : 파라미터가 없는 생성자(기본생성자) 생성
//	@AllArgsConstructor : 모든 파라미터가 있는 생성자 생성
//	@RequiredArgsConstructor : 초기화되지 않은 Final, @NonNull 붙은 필드 생성자 생성
//	@Log : log 자동변수 생성
//	@Value : 불변(값이 변하지 않음, 변경할 수 없는) 클래스 생성
//	@Data  : @ToString @EqualsAndHashCode @Getter/@Setter 
//	         @RequiredArgsConstructor  합친 어노테이션
	

	private String id;
	private String pass;
	private String name;
	private Timestamp date;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
