package com.ssafy.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.house.model.dto.CommercialDto;
import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.dto.HouseInfoDto;

public interface HouseMapper {
	
	public List<HouseDto> selectList(@Param("startRow") int startRow, @Param("count")int count);
	public int selectCount();
	public List<HouseDto> searchList(@Param("startRow") int startRow, @Param("count")int count,@Param("opt") String opt, @Param("val") String val);
	public int searchCount(@Param("opt") String opt, @Param("val") String val);
	public HouseDto selectOne(@Param("no") int no);
	public HouseInfoDto selectPlace(@Param("AptName") String AptName,@Param("dong") String dong);
	public List<CommercialDto> comList(@Param("lat") String lat,@Param("lng")String lng);
}
