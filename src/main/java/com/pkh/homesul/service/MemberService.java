package com.pkh.homesul.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
