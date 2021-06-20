package com.ssafy.house.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.house.model.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(@Param("login_user_id") String login_user_id,@Param("login_user_pwd") String login_user_pwd);

	public int checkCode(String businessCode);

	public int join(MemberDto member);

	public void delete(String userid);

	public boolean modify(MemberDto member);

}
