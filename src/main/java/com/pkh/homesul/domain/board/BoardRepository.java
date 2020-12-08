package com.pkh.homesul.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BoardRepository extends JpaRepository<Board1, Integer>{
	
	@Query(value = "SELECT * FROM board1 WHERE bno = :bno", nativeQuery = true)
	Board1 findByBno(int bno);

	@Modifying
	@Query(value="DELETE FROM board1 WHERE bno = :bno", nativeQuery = true)
	int DeleteByBno(int bno);
}
