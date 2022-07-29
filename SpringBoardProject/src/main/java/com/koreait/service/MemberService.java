package com.koreait.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.koreait.dto.MemberDTO;
import com.koreait.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;
	
	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberDTO login(HashMap<String, String> map) {
		return mapper.login(map);
	}

}
