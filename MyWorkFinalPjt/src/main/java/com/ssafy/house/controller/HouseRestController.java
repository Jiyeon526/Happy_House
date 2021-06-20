package com.ssafy.house.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.house.model.dto.HouseInfoDto;
import com.ssafy.house.model.service.HouseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseRestController {
	@Autowired
	private HouseService hservice;
	
	@GetMapping("/houselist")
	public ResponseEntity<Map<String, Object>> list(@RequestParam(value="page", defaultValue="1") int page){
		Map<String, Object> result = hservice.makePage(page);
		
		if(result == null) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}
	return new ResponseEntity<Map<String,Object>>(hservice.makePage(page),HttpStatus.OK);
		
	}
	
	@GetMapping("/housesearch")
	public ResponseEntity<Map<String, Object>> searchPage(@RequestParam(defaultValue="1") int page ,@RequestParam("opt") String opt,@RequestParam("val") String val ) {
		Map<String, Object> result = hservice.makeSearchPage(page, opt, val);
		return new ResponseEntity<Map<String,Object>>(hservice.makeSearchPage(page, opt, val),HttpStatus.OK);
	}
	
	@GetMapping("/houseread")
	public ResponseEntity<Map<String,Object>> readPage(@RequestParam("no") int no){
		Map<String,Object>  Info = hservice.readInfo(no);
		
		if(Info != null)
			return new ResponseEntity<Map<String,Object>>(Info,HttpStatus.OK);
		return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
	}
}

