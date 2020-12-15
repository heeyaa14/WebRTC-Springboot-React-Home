package com.pkh.homesul.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import com.pkh.homesul.domain.board.Board1;
import com.pkh.homesul.domain.member.Member1;
import com.pkh.homesul.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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

	public void certifiedPhoneNumber(String inputPhoneNumber, String cerNum) {
		// TODO Auto-generated method stub
		String api_key = "NCSZW8PLXPXD3BM";
        String api_secret = "5DPX5JPARI0DPZFTGWDGGUPVI6SD2ZBZ";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", inputPhoneNumber);    // 수신전화번호
        params.put("from", "01040200358");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "핫띵크 휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
	}

}
