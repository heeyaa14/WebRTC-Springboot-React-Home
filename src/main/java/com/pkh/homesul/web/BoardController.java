package com.pkh.homesul.web;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final HttpSession session;
	
	@GetMapping("/boardForm")
	public String BoardForm() {
		return "boardForm";
	}
	
	@PostMapping("/write")
	public String write(@RequestBody Board1 board) {
		boardService.글쓰기(board);
		return "ok";
	}
	
	@GetMapping("/boardList")
	public Page<Board1> boardList(@PageableDefault(size=5, sort="bno", direction = Direction.DESC) Pageable pageable) {
		Page<Board1> board = boardService.게시글목록(pageable);
		return board;
	}
	
	@GetMapping("/boardDetail/{bno}") //글상세보기 (글수정 시 정보 들고옴)
	public Board1 boardDetail(@PageableDefault(size = 5, sort = "bno", direction = Direction.DESC) Pageable pageable,@PathVariable int bno){
		Board1 board = boardService.글상세보기(pageable, bno);
		return board;
		
	}
	
	@PutMapping("/update/{bno}")
	public String updateBoard(@PathVariable int bno, @RequestBody Board1 board) {
		boardService.글수정하기(bno, board);
		return "ok";
	}
	
	@DeleteMapping("/delete/{bno}")
	public String deleteBoard(@PathVariable int bno) {
		boardService.글삭제하기(bno);
		return "ok";
	}
	
	
}
