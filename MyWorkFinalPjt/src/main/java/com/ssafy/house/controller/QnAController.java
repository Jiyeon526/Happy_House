package com.ssafy.house.controller;

import java.util.List;

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

import com.ssafy.house.model.dto.QnACommentDto;
import com.ssafy.house.model.dto.QnADto;
import com.ssafy.house.model.service.QnAService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/house")
public class QnAController {
	
	@Autowired
	private QnAService qService;
	
	@GetMapping("/community/qna")
	public ResponseEntity<List<QnADto>> main(){
		List<QnADto> result = qService.all();
		
		if(result == null)
			return new ResponseEntity<List<QnADto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<QnADto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/community/qna/vuelist")
	public ResponseEntity<String> write(@RequestBody QnADto dto){
		boolean result = qService.write(dto);
		if(result)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/community/qna/vuelist/{bnum}")
	public ResponseEntity<QnADto> read(@PathVariable int bnum){
		QnADto result = qService.read(bnum);
		if(result == null)
			return new ResponseEntity<QnADto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QnADto>(result, HttpStatus.OK);
	}
	
	@PutMapping("/community/qna/vuelist")
	public ResponseEntity<String> update(@RequestBody QnADto dto){
		boolean result = qService.update(dto);
		if(result)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/community/qna/vuelist/{bnum}")
	public ResponseEntity<String> delete(@PathVariable int bnum){
		boolean result = qService.delete(bnum);
		if(result)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/community/qna/comment/{bnum}")
	public ResponseEntity<List<QnACommentDto>> commentMain(@PathVariable int bnum){
		List<QnACommentDto> result = qService.commentAll(bnum);
		
		if(result == null)
			return new ResponseEntity<List<QnACommentDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<QnACommentDto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/community/qna/comment")
	public ResponseEntity<String> postComment(@RequestBody QnACommentDto dto){
		System.out.println(dto.toString());
		if(qService.writeComment(dto))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
