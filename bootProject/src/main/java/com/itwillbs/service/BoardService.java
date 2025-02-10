package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwillbs.entity.Board;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.BoardRepository;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class BoardService {
	//BoardRepository 객체생성
	private final BoardRepository boardRepository;

	public void insertBoard(Board board) {
		log.info("BoardService insertBoard()");
		// 조회수, 글쓴날짜
		board.setReadcount(0);
		board.setDate(new Timestamp(System.currentTimeMillis()));
		
		boardRepository.save(board);
	}

	public Page<Board> getBoardList(Pageable pageable) {
		log.info("BoardService getBoardList()");
		
		return boardRepository.findAll(pageable);
	}

	public Optional<Board> findById(int num) {
		log.info("BoardService findById()");
		
		return boardRepository.findById(num);
	}

	public void boardBoard(Board board) {
		log.info("BoardService boardBoard()");
		
		boardRepository.save(board);
	}

	public void deleteById(int num) {
		log.info("BoardService deleteById()");
		
		boardRepository.deleteById(num);
	}

	public Page<Board> findBySubjectContaining(String search, Pageable pageable) {
		log.info("BoardService findBySubjectContaining()");
		
		return boardRepository.findBySubjectContaining(search,pageable);
	}
	
	
}
