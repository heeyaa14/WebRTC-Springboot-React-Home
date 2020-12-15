package com.pkh.homesul.web;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.domain.member.Member1;
import com.pkh.homesul.service.MemberService;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class MemberController {
	private final MemberService memberService;
	private final HttpSession session;
	
	// 본인인증 문자전송
	@PostMapping("/sendSMS")
    public @ResponseBody
    String sendSMS(String inputPhoneNumber) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        
        System.out.println("수신자 번호 : " + inputPhoneNumber);
        System.out.println("인증번호 : " + numStr);
        memberService.certifiedPhoneNumber(inputPhoneNumber,numStr);
        return "ok";
    }
	
	@GetMapping("/sendSMS")
    public @ResponseBody
    String sendSMS2(String inputPhoneNumber) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        
        System.out.println("수신자 번호 : " + inputPhoneNumber);
        System.out.println("인증번호 : " + numStr);
        memberService.certifiedPhoneNumber(inputPhoneNumber,numStr);
        return numStr;
    }
	
	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> saveMember(@RequestBody Member1 member) {
		memberService.회원가입(member);
		return new ResponseEntity<String>("ok", HttpStatus.CREATED);
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		session.invalidate();
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@GetMapping("/memberList")
	public List<Member1> memberList() {
		List<Member1> member = memberService.멤버목록();
		return member;
	}
}
