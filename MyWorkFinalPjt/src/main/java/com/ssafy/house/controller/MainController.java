package com.ssafy.house.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements ErrorController {
	@RequestMapping("/")
	String main() {
		return "/index.html";
	}
	
	
	@RequestMapping("/error")
	String css(HttpServletRequest req) {
		System.out.println("req:"+req.getRequestURI());
		return "index.html";
	}


	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
}
