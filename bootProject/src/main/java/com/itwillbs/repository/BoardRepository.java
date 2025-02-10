package com.itwillbs.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	// 상속 
	// extends JpaRepository<T(Entity), ID(기본키)>
	// JpaRepository 지원하는 기본 메서드
	// save(Entity) : 엔티티 저장 및 수정
	// void delete(Entity) : 엔티티 삭제
	// count : 엔티티 총 개수 반환
	// List<Entity> findAll() : 모든 엔티티 조회 
	// Entity findById(id) : id(primary key)에 대한 엔티티 조회
	
	// 쿼리 메서드 정의
	// 아이디 비밀번호 조회
	// findByIdAndPass(id,pass)  =>     where id = ? and pass = ?
	// findByIdOrPass(id,pass)  =>     where id = ? or pass = ?
	// findByNumBetween()  =>     where num  between ? and ?
	
	Page<Board> findBySubjectContaining(String search, Pageable pageable);
	
//	Hibernate: 
//	    select
//	        b1_0.num,
//	        b1_0.content,
//	        b1_0.date,
//	        b1_0.file,
//	        b1_0.name,
//	        b1_0.readcount,
//	        b1_0.subject 
//	    from
//	        board b1_0 
//	    where
//	        b1_0.subject like ? escape '\\' 
//	    order by
//	        b1_0.num desc 
//	    limit
//	        ?, ?
//	Hibernate: 
//	    select
//	        count(b1_0.num) 
//	    from
//	        board b1_0 
//	    where
//	        b1_0.subject like ? escape '\\'
	
	
	
}
