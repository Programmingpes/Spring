package com.koreait.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.koreait.dto.MemberDTO;
import com.koreait.dto.QnADTO;
import com.koreait.mapper.MemberMapper;
import com.koreait.mapper.QnAMapper;

@Service
public class QnAService {
	private QnAMapper mapper;
	
	public QnAService(QnAMapper mapper) {
		this.mapper = mapper;
	}

	public int insertQnA(QnADTO dto) {
		return mapper.insertQnA(dto);
	}

	public List<QnADTO> selectQnAlist(Map<String, Object> map) {
		return mapper.selectQnAlist(map);
	}

	public int selectAllCount() {
		return mapper.selectAllCount();
	}

	public List<QnADTO> selectAllQnAlist(int page) {
		return mapper.selectAllQnAlist(page);
	}

	public QnADTO selectQnA(int qno) {
		return mapper.selectQnA(qno);
	}

	public int updateAnswer(int qno, String response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qno", qno);
		map.put("response", response);
		return mapper.updateAnswer(map);
	}

	public int updateViewStatus(int qno) {
		return mapper.updateViewStatus(qno);
	}

	public int updateAnswerStatus(int qno) {
		return mapper.updateAnswerStatus(qno);
	}
}
