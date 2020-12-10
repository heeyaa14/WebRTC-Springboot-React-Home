package com.pkh.homesul.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member1, Integer>{

	Member1 findByIdAndPassword(String id, String password);

	Member1 getById(String id);
}
