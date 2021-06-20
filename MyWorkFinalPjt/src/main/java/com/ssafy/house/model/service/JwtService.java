package com.ssafy.house.model.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ssafy.house.model.dto.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	
//	@Value("${jwt.salt}")
	private String salt = "MYSALT";
	
//	@Value("${jwt.expmin}")
	private Long expireMin = (long) 5;
	
	public String create(MemberDto member) { // 로그인 성공시 토큰 반환
		System.out.println("time: " + expireMin);
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		builder.setSubject("로그인토큰")
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
			.claim("loginMember", member); // 여기도 바꾸깅
		
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		String jwt = builder.compact();
		System.out.println("토큰 발행: " + jwt);
		return jwt;
	}
	
	public boolean checkValid(String jwt) { // 토큰이 제대로인지 확인
		System.out.println("토큰 점검: " + jwt);
		
		Claims claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody();
		return true;
	}
	
	public Map<String, Object> get(String jwt){ // 토큰 분석해서 필요한 정보 반환
		Jws<Claims> claims = null;
		claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		System.out.println("claims: " + claims);
		return claims.getBody();
	}
}
