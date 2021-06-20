package com.ssafy.house.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dto.CommercialDto;
import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.dto.HouseInfoDto;
import com.ssafy.house.model.mapper.HouseMapper;

@Service
public class HouseService {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final int COUNT_PER_PAGE=10;
	public Map<String, Object> makePage(int page){
		// 총 게시글 갯수 디비에서 조회함.
		int totalCount = sqlSession.getMapper(HouseMapper.class).selectCount();
		
		// 총 페이지수 계산
		int totalPage = totalCount/COUNT_PER_PAGE;
		if(totalCount%COUNT_PER_PAGE>0)
			totalPage++;
		
		// 화면 하단의 시작 페이지
		int startPage = (page-1)/10*10+1;
				
		int endPage = startPage+9;
		if(endPage>totalPage)
			endPage = totalPage;
		
		// 현재 페이지 게시글 조회
		int startRow = (page-1)*COUNT_PER_PAGE; // limit 의 시작행 번호 계산.
		List<HouseDto> bList = sqlSession.getMapper(HouseMapper.class).selectList(startRow, COUNT_PER_PAGE);
		
		System.out.println(bList.get(0).getAptName());
		Map<String, Object> pageInfo = new HashMap<>();
		
		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);
		pageInfo.put("totalPage", totalPage);
		pageInfo.put("curPage", page);
		pageInfo.put("bList", bList);
	
		return pageInfo;
	}
	
	public Map<String, Object> makeSearchPage(int page,String opt,String val){
		// 총 게시글 갯수 디비에서 조회함.
		int totalCount = sqlSession.getMapper(HouseMapper.class).searchCount(opt,val);
		
		// 총 페이지수 계산
		int totalPage = totalCount/COUNT_PER_PAGE;
		if(totalCount%COUNT_PER_PAGE>0)
			totalPage++;
		
		// 화면 하단의 시작 페이지
		int startPage = (page-1)/10*10+1;
				
		int endPage = startPage+9;
		if(endPage>totalPage)
			endPage = totalPage;
		
		// 현재 페이지 게시글 조회
		int startRow = (page-1)*COUNT_PER_PAGE; // limit 의 시작행 번호 계산.
		List<HouseDto> bList = sqlSession.getMapper(HouseMapper.class).searchList(startRow, COUNT_PER_PAGE,opt,val);
		
		Map<String, Object> pageInfo = new HashMap<>();
		
		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);
		pageInfo.put("totalPage", totalPage);
		pageInfo.put("curPage", page);
		pageInfo.put("bList", bList);
	
		return pageInfo;
	}
	
	public Map<String,Object> readInfo(int no){
		
		HouseDto house = sqlSession.getMapper(HouseMapper.class).selectOne(no);
		
		HouseInfoDto place = sqlSession.getMapper(HouseMapper.class).selectPlace(house.getAptName(),house.getDong());
		List<CommercialDto> cList = sqlSession.getMapper(HouseMapper.class).comList(place.getLat(), place.getLng());
		Map<String, Object> houseInfo = new HashMap<>();
		houseInfo.put("house", house);
		houseInfo.put("place", place);
		houseInfo.put("cList", cList);
		return houseInfo;
	}
}
