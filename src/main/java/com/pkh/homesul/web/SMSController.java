package com.pkh.homesul.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pkh.homesul.domain.member.Coolsms;

@Controller
@RequestMapping(value="/MsgSend")
public class SMSController {

	// 본인인증 SMS전송
		public String sendSms(HttpServletRequest request) throws Exception {
			String api_key = "NCSZW8PLXPXD3BM";
			String api_secret = "5DPX5JPARI0DPZFTGWDGGUPVI6SD2ZBZ";
			
			Coolsms coolsms = new Coolsms(api_key, api_secret);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("to", "01040200358");
			map.put("phone", (String)request.getParameter("phone"));	// Form에서 전송한 발신번호를 map에 저장
			map.put("text", (String)request.getParameter("text"));		// Form에서 전송한 문자내용을 map에 저장
			map.put("type", "sms");
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
		
}
