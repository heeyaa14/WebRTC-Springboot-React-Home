package com.pkh.homesul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.domain.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// 게시글 등록
	@Transactional
	public void 글쓰기(Board1 board) {
		// TODO Auto-generated method stub
		boardRepository.save(board);
	}

	// 게시글 리스트
	@Transactional(readOnly = true)
	public Page<Board1> 게시글목록(Pageable pageable) {
		Page<Board1> boards = boardRepository.findAll(pageable);
		String content;
		for(Board1 board: boards) {
			content = board.getContent();
			if(content != null)
				content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
			board.setContent(content);
		}
		return boards;
	}

	// 게시글 상세페이지
	public Board1 글상세보기(Pageable pageable, int bno) {
		// TODO Auto-generated method stub
		return boardRepository.findById(bno).get();
	}

	// 게시글 수정
	@Transactional
	public void 글수정하기(int bno, Board1 board) {
		// TODO Auto-generated method stub
		Board1 boardEntity = boardRepository.findByBno(bno);
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
	}
		
	// 게시글 삭제
	@Transactional
	public void 글삭제하기(int bno) {
		// TODO Auto-generated method stub
		boardRepository.DeleteByBno(bno);
	}
	
	
}
