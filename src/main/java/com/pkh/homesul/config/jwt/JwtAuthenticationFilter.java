package com.pkh.homesul.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkh.homesul.domain.member.Member1;
import com.pkh.homesul.domain.member.MemberRepository;


public class JwtAuthenticationFilter implements Filter{

	private MemberRepository personRepository;
	
	public JwtAuthenticationFilter(MemberRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("JwtAuthenticationFilter 작동");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		PrintWriter out = resp.getWriter();
		
		String method = req.getMethod();
		System.out.println(method);
		if(!method.equals("POST")) {
			System.out.println("post 요청이 아니기 때문에 거절");
			out.print("required post method");
			out.flush();
		}else {
			System.out.println("post 요청이 맞습니다.");
			
			ObjectMapper om = new ObjectMapper();
			try {
				Member1 person = om.readValue(req.getInputStream(), Member1.class);
				System.out.println(person); 
				
				// 1번 username, password를 DB에 던짐
				Member1 personEntity = 
				personRepository.findByIdAndPassword(person.getId(), person.getPassword());
				// 2번 값이 있으면 있다?. 없다?
				if(personEntity == null) {
					System.out.println("아이디 혹은 패스워드가 틀렸습니다.");	
					out.print("fail");
					out.flush();
				}else {
					System.out.println("인증되었습니다.");
					
					String jwtToken = 
							JWT.create()
							.withSubject("토큰제목")
							.withExpiresAt(new Date(System.currentTimeMillis()+JwtProps.halfhour))
							.withClaim("no", personEntity.getMno())
							.withClaim("id", personEntity.getId())
							.sign(Algorithm.HMAC512(JwtProps.secret));
					
					resp.addHeader(JwtProps.header,JwtProps.auth+jwtToken);
					out.print("ok");
					out.flush();
				}
			} catch (Exception e) {
				System.out.println("오류 : "+e.getMessage());
			}
			
//			StringBuilder data = new StringBuilder();
//			BufferedReader br = req.getReader();
//			String input = "";
//			while((input = br.readLine()) != null) {
//				data.append(input);
//			}
//			System.out.println(data.toString());
//			
//			Gson gson = new Gson();
//			Person person = gson.fromJson(data.toString(), Person.class);
//			System.out.println(person);
		}
	}
}
