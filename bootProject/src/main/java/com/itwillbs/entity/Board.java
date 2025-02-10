package com.itwillbs.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "board")
@Getter
@Setter
@ToString
public class Board {
	//primary key, 글번호 자동으로 1씩 증가, 수정 false
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num", updatable = false)
	private int num;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="content")
	private String content;
	
	@Column(name="readcount")
	private int readcount;
	
	@Column(name = "date")
	private Timestamp date;
	
	@Column(name = "file")
	private String file;

}
