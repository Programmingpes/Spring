package com.koreait.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, String> map);
	int insertMember(MemberDTO dto);
	List<MemberDTO> selectAllMember();
	int deleteMember(String id);
	int updateMember(MemberDTO dto);
	List<MemberDTO> selectMember(Map<String, Object> map);
	
}
