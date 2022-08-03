package com.student.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.student.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> searchStudent(Map<String, Object> map);
	List<StudentDTO> selectAllStudent();
	void sendLog(Map<String, Object> map);

}
