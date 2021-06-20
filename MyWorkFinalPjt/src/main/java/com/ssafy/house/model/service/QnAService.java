package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dto.QnACommentDto;
import com.ssafy.house.model.dto.QnADto;
import com.ssafy.house.model.mapper.QnAMapper;

@Service
public class QnAService {
	@Autowired
	private SqlSession dao;
	
	public List<QnADto> all(){
		return dao.getMapper(QnAMapper.class).all();
	}

	public boolean write(QnADto dto) {
		return dao.getMapper(QnAMapper.class).write(dto);
	}

	public QnADto read(int bnum) {
		return dao.getMapper(QnAMapper.class).read(bnum);
	}

	public boolean update(QnADto dto) {
		return dao.getMapper(QnAMapper.class).update(dto);
	}

	public boolean delete(int bnum) {
		return dao.getMapper(QnAMapper.class).delete(bnum);
	}

	public List<QnACommentDto> commentAll(int bnum) {
		return dao.getMapper(QnAMapper.class).commentAll(bnum);
	}

	public boolean writeComment(QnACommentDto dto) {
		return dao.getMapper(QnAMapper.class).postComment(dto);
	}
}
