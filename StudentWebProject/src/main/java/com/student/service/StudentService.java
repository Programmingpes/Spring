package com.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.student.dto.StudentDTO;
import com.student.mapper.StudentMapper;

@Service
public class StudentService {
	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		this.mapper = mapper;
	}

	public List<StudentDTO> searchStudent(String kind, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchStudent(map);
	}

	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	public void sendLog(String date, String code, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("code", code);
		map.put("content", content);
		mapper.sendLog(map);
	}
	
	
}




