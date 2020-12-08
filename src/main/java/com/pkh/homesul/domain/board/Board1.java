package com.pkh.homesul.domain.board;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.pkh.homesul.domain.member.Member1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Board1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	@Column(unique = true)
	private String title;
	private String content;
	private String writer;
	@CreationTimestamp
	private Timestamp reg_date;
	
	@JoinColumn(name="mno")
	@ManyToOne //foreign key 설정
	private Member1 member;
	
	public String  getReg_date() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 String dateFormated = dateFormat.format(reg_date);
		     return dateFormated;
			
			}catch(Exception e){
				e.getMessage();
				return null;
			}
	}

}
