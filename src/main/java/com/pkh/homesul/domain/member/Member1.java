package com.pkh.homesul.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
public class Member1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mno;
	@Column(unique = true)
	private String id;
	private String password;
	private String username;
	private String email;
	private String phone;
	private String gender;
	private String status;
}
