package com.ssafy.house.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.house.model.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{

	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception{
		System.out.println(request.getMethod() + ": " + request.getServletPath());
		System.out.println("interceptor");
		
//		Enumeration<String> en = request.getHeaderNames();
//		while(en.hasMoreElements())
//			System.out.println("header:"+en.nextElement());
		
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}else {
			String token = request.getHeader("authorization");//Authorization Authorization
			System.out.println("인터셉터 토큰: " + token);
			if(token != null && token.length() > 0 && jwtService.checkValid(token)) {
				System.out.println("토큰 사용 가능: " + token);
				return true;
			}else {
				throw new RuntimeException("인증 토큰 없음");
			}
		}
	}
}
