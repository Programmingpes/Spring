package com.koreait.mapper;


import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, String> map);

	
}
