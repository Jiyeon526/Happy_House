package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dto.NoticeDto;
import com.ssafy.house.model.mapper.NoticeMapper;

@Service
public class NoticeService {

	@Autowired
	private SqlSession dao;
	
	public List<NoticeDto> all() {
		return dao.getMapper(NoticeMapper.class).all();
	}

	public boolean write(NoticeDto dto) {
		return dao.getMapper(NoticeMapper.class).write(dto);
	}

	public NoticeDto read(int nno) {
		return dao.getMapper(NoticeMapper.class).read(nno);
	}

	public boolean update(NoticeDto dto) {
		return dao.getMapper(NoticeMapper.class).update(dto);
	}

	public boolean delete(int nno) {
		return dao.getMapper(NoticeMapper.class).delete(nno);
	}

	public void add(int nno, int views) {
		dao.getMapper(NoticeMapper.class).add(nno, views);
	}

}
