package com.ssafy.house.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dto.MemberDto;
import com.ssafy.house.model.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlSession;

	public MemberDto login(String login_user_id, String login_user_pwd) {
		System.out.println(login_user_id+"/"+login_user_pwd);
		if(login_user_id == null || login_user_pwd == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(login_user_id, login_user_pwd);
	}

	public int join(MemberDto member) {
		return sqlSession.getMapper(MemberMapper.class).join(member);
	}

//
//	public boolean check(MemberDto member, String userpwd) {
//		if(member.getUserpwd().equals(userpwd))
//			return true;
//		return false;
//	}
//
	public void delete(String userid) {
		sqlSession.getMapper(MemberMapper.class).delete(userid);
		
	}

	public boolean modify(MemberDto member) {
		return sqlSession.getMapper(MemberMapper.class).modify(member);
	}
	
}
