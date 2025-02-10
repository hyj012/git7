package com.itwillbs.controller;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.entity.Board;
import com.itwillbs.entity.Food;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.FoodRepository;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class BoardController {
	//객체생성
	private final BoardService boardService;
	
//	연관관계매핑
	private final FoodRepository foodRepository;
	
	//  application.properties  경로 지정
	//	uploadPath = D:/Shared/JSP/workspace_sts4/bootProject/src/main/resources/static/upload
	
	@Value("${uploadPath}")
	String uploadPath;
	
	//가상주소 http://localhost:8080/boardWrite
	// => /board/write.html 이동
	@GetMapping("/boardWrite")
	public String boardWrite() {
		log.info("BoardController boardWrite()");

//		연관관계매핑 => 연습
//		insert into customer(name) values("kim");
//		insert into Food(name,price,customer_id) values("라면",1000,1);

//		foodRepository.findById(1L);
		
//	    select
//	    	f1_0.id,
//	    	c1_0.id,
//	    	c1_0.name,
//	    	f1_0.name,
//	    	f1_0.price 
//		from
//	    	food f1_0 
//		left join
//	    	customer c1_0 
//	        	on c1_0.id=f1_0.customer_id 
//		where
//	    	f1_0.id=?
		
		return "/board/write";
	}
	
	@PostMapping("/boardWrite")
	public String boardWritePost(@RequestParam("name") String name,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file) throws Exception{
		log.info("BoardController boardWritePost()");
		log.info(uploadPath);
		log.info(name);
		log.info(subject);
		log.info(content);
		log.info(file.getOriginalFilename());
		
		UUID uuid = UUID.randomUUID();
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		log.info(extension); //  확장자  .jpg
		String filename = uuid.toString()+extension;
		log.info(filename); //797b1fcc-7b00-444c-8add-88f67a7c3263.jpg
		
		String fileUploadFullUrl = uploadPath + "/" + filename;
		log.info(fileUploadFullUrl);
		//D:/Shared/JSP/workspace_sts4/bootProject/src/main/resources/static/upload/81a0a91f-3514-418c-a6fd-7bb58041b390.jpg
		
		FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename));
		
		Board board = new Board();
		board.setName(name);
		board.setSubject(subject);
		board.setContent(content);
		board.setFile(filename);
		
		boardService.insertBoard(board);
		
		return "redirect:/boardList";
	}
	
	//가상주소 http://localhost:8080/boardList
	//가상주소 http://localhost:8080/boardList?page=2
	//가상주소 http://localhost:8080/boardList?page=2&search=검색어
	//가상주소 http://localhost:8080/boardList?search=검색어
	@GetMapping("/boardList")
	public String boardList(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "3", required = false) int size,
			@RequestParam(value = "search", defaultValue = "", required = false) String search) {
		// 페이지번호 page => 현 페이지 번호
		// 한화면에 보여줄 글 개수 => size
//		int size = 10;
		
		log.info("BoardController boardList()");
//		import org.springframework.data.domain.Page;
//		import org.springframework.data.domain.PageRequest;
//		import org.springframework.data.domain.Pageable;
//		import org.springframework.data.domain.Sort;

		// PageRequest 에서는 page 0부터 시작 => page-1 설정
		Pageable pageable = PageRequest.of(page-1, size, Sort.by("num").descending());
		
//		Page<Board> boardList = boardService.getBoardList(pageable);
		
		Page<Board> boardList = boardService.findBySubjectContaining(search,pageable);
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		//전체 페이지 개수
		model.addAttribute("totalPages", boardList.getTotalPages());
		
		//한화면에 보여줄 페이지 개수 설정
		int pageBlock = 3;
		int startPage = (page-1)/pageBlock*pageBlock+1;
		int endPage=startPage + pageBlock - 1;
		if(endPage > boardList.getTotalPages()) {
			endPage = boardList.getTotalPages();
		}
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("search", search);
		
		return "/board/list";
	}

	
	//가상주소 http://localhost:8080/content?num=1
	// => /board/content.html 이동
	@GetMapping("/content")
	public String content(@RequestParam("num") int num, Model model) {
		log.info("BoardController content()");
		
		Optional<Board> board = boardService.findById(num);
		
		model.addAttribute("board", board.get());
		
		return "/board/content";
	}
	
	//가상주소 http://localhost:8080/boardUpdate?num=1
	// => /board/update.html 이동
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam("num") int num, Model model) {
		log.info("BoardController boardUpdate()");
		
		Optional<Board> board = boardService.findById(num);
		
		model.addAttribute("board", board.get());
		
		return "/board/update";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdatePost(Board board) {
		log.info("BoardController boardUpdatePost()");
		
		boardService.boardBoard(board);
		
		return "redirect:/boardList";
	}
	
	//가상주소 http://localhost:8080/boardDelete?num=1
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam("num") int num) {
		log.info("BoardController boardDelete()");
		
		boardService.deleteById(num);
		
		return "redirect:/boardList";
	}
	
	
	
	
}
