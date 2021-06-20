package com.ssafy.house.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.model.dto.NoticeDto;
import com.ssafy.house.model.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/house")
public class NoticeController {
	
	@Autowired
	private NoticeService nservice;
	
	@GetMapping("/community/notice")
	public ResponseEntity<List<NoticeDto>> main(){
		List<NoticeDto> result = nservice.all();
		
		if(result == null)
			return new ResponseEntity<List<NoticeDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<NoticeDto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/community/notice/vuelist")
	public ResponseEntity<String> write(@RequestBody NoticeDto dto){
		boolean result = nservice.write(dto);
		if(result)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/community/notice/vuelist/{nno}")
	public ResponseEntity<NoticeDto> read(@PathVariable int nno){ //views추가
		NoticeDto result = nservice.read(nno);
		if(result == null)
			return new ResponseEntity<NoticeDto>(HttpStatus.NO_CONTENT);
		else {
			nservice.add(nno, result.getNview()+1);
			return new ResponseEntity<NoticeDto>(result, HttpStatus.OK);
		}
	}
	
	@PutMapping("/community/notice/vuelist")
	public ResponseEntity<String> update(@RequestBody NoticeDto dto){
		System.out.println(dto.toString());
		boolean result = nservice.update(dto);
		if(result)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/community/notice/vuelist/{nno}")
	public ResponseEntity<String> delete(@PathVariable int nno){
		boolean result = nservice.delete(nno);
		if(result)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
}
