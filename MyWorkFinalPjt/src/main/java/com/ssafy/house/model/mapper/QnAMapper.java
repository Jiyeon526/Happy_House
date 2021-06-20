package com.ssafy.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.dto.QnACommentDto;
import com.ssafy.house.model.dto.QnADto;

@Mapper
public interface QnAMapper {

	public List<QnADto> all();

	public boolean write(QnADto dto);

	public QnADto read(int bnum);

	public boolean update(QnADto dto);

	public boolean delete(int bnum);

	public List<QnACommentDto> commentAll(int bnum);

	public boolean postComment(QnACommentDto dto);

}
