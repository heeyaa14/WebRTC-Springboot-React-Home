package com.pkh.homesul.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.domain.member.Member1;
import com.pkh.homesul.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Transactional
	public void 회원가입(Member1 member) {
		memberRepository.save(member);
	}
	
	@Transactional(readOnly = true)
	public List<Member1> 멤버목록() {
		List<Member1> members = memberRepository.findAll();
		return members;
	}

}
