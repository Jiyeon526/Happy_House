package com.ssafy.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.house.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	public List<NoticeDto> all();

	public boolean write(NoticeDto dto);

	public NoticeDto read(int nno);

	public boolean update(NoticeDto dto);

	public boolean delete(int nno);

	public void add(@Param("nno") int nno,@Param("views") int views);
	
}
