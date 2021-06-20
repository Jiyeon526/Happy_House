package com.ssafy.house.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.house.model.dto.MemberDto;
import com.ssafy.house.model.service.JwtService;
import com.ssafy.house.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/house")
public class MemberController {
	@Autowired
	private MemberService mservice;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/member/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginInfo, HttpServletResponse res){
//		System.out.println("controller login: " + loginInfo);
//		System.out.println(loginInfo.get("id") + loginInfo.get("password"));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			MemberDto loginMember = mservice.login(loginInfo.get("id"), loginInfo.get("password"));
			if(loginMember == null) {
				System.out.println("로그인 실패");
				resultMap.put("message", "fail");
				status = HttpStatus.BAD_REQUEST;
			}else {
				String token = jwtService.create(loginMember);

				resultMap.put("status", true);
				resultMap.put("data", loginMember);
				System.out.println(loginMember);
				resultMap.put("jwt_auto_token", token);
				status = HttpStatus.ACCEPTED;
			}

		}catch(RuntimeException e) {
			System.out.println("로그인 실패");
			resultMap.put("message", "fail");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);	
	}
	
	@PostMapping("/member/check")
	public ResponseEntity<Map<String, Object>> check(@RequestBody String token){
		jwtService.checkValid(token);
		Map<String, Object> result = jwtService.get(token);
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/member/join")
	public ResponseEntity<String> join(@RequestBody MemberDto member){
		int check = 1;
		System.out.println("회원가입 입력: " + member.toString());
		if(check > 0 && mservice.join(member) > 0) {
			if(member.getRole().equals("business")) {
				return new ResponseEntity<String>("success_business", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/info/profile")
	public ResponseEntity<Map<String, Object>> infoProfile(@RequestBody String token){
		jwtService.checkValid(token);
		Map<String, Object> result = jwtService.get(token);
		
		System.out.println("profile loginInfo: " + result.get("loginMember"));
		
		if(result != null) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@DeleteMapping("/info/profile")
	public ResponseEntity<String> delete(@RequestBody Map<String, String> tokenInfo) {
		System.out.println("token: " + tokenInfo.get("token"));
		Map<String, Object> result = jwtService.get(tokenInfo.get("token"));
		Map<String, Object> member = (Map<String, Object>) result.get("loginMember");
		
		System.out.println("delete userid: " + member.get("userid"));
		mservice.delete((String)member.get("userid"));
		return new ResponseEntity<String>("회원 정보를 삭제했습니다.", HttpStatus.OK);
	}
	
	@PutMapping("/info/profile")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, Object> info) {
		System.out.println("info: " + info.get("info"));
		MemberDto member = new MemberDto();
		Map<String, Object> m = (Map<String, Object>) info.get("info");
		member.setUserid((String) m.get("userid"));
		member.setUsername((String) m.get("username"));
		member.setUserpwd((String) m.get("userpwd"));
		member.setEmail((String) m.get("email"));
		member.setAddress((String) m.get("address"));
//		member.setBusinessCode((String) m.get("businessCode"));
		member.setAge(Integer.parseInt((String) m.get("age")));
		member.setRole((String) m.get("role"));
		System.out.println(member.toString());
		Map<String, Object> resultMap = new HashMap<>();
		if(mservice.modify(member)) {
			String token = jwtService.create(member);
			
			resultMap.put("status", true);
			resultMap.put("data", member);
			resultMap.put("jwt_auto_token", token);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}else {
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
		}

	}
}
