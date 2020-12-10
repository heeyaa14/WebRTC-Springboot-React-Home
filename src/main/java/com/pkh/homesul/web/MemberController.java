package com.pkh.homesul.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.domain.member.Coolsms;
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
	
	// 본인인증 SMS전송
	@PostMapping("/join/MsgSend")
	public String sendSms(HttpServletRequest request) throws Exception {
		String api_key = "NCSZW8PLXPXD3BM";
		String api_secret = "5DPX5JPARI0DPZFTGWDGGUPVI6SD2ZBZ";
		
		Message coolsms = new Message(api_key, api_secret);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("to", "01040200358");
		map.put("phone", (String)request.getParameter("phone"));	// Form에서 전송한 발신번호를 map에 저장
		map.put("text", (String)request.getParameter("text"));		// Form에서 전송한 문자내용을 map에 저장
		map.put("type", "SMS");
		System.out.println(map);
		
		JSONObject result = coolsms.send(map);	// 문자보내기&전송결과 받기
		
		if( (boolean)result.get("status") == true ) {
			
			// 메시지보내기 성공 및 전송결과 출력
			System.out.println("성공");
			System.out.println(result.get("group_id"));
			System.out.println(result.get("result_code"));
			System.out.println(result.get("result_message"));
			System.out.println(result.get("success_count"));
			System.out.println(result.get("error_count"));
		} else {
			// 메시지보내기 실패
			System.out.println("실패");
			System.out.println(result.get("code"));
			System.out.println(result.get("message"));
		}
		
		return "sendSms";
	}
	
	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> save(@RequestBody Member1 member) {
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
