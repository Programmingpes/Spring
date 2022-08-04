package com.koreait.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.FileDTO;


@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList(int pageNo);

	int selectAllCount();

	BoardDTO selectBoardDTO(int bno);

	List<BoardCommentDTO> selectCommentDTO(int bno);

	void addBoardCount(int bno);

	List<FileDTO> selectFileList(int bno);

	int deleteBoard(int bno);

	int addBoardComment(BoardCommentDTO dto);
	
	int insertBoardLike(Map<String, Object> map);
	
	int deleteBoardLike(Map<String, Object> map);
	
	int insertBoardHate(Map<String, Object> map);
	
	int deleteBoardHate(Map<String, Object> map);

	int boardWrite(BoardDTO dto);

	int selectBno();

	void insertFile(FileDTO file);

	FileDTO selectFile(HashMap<String, Integer> map);
	
	int deleteBoardComment(int cno);
	
	int insertBoardCommentLike(Map<String, Object> map);
	
	int deleteBoardCommentLike(Map<String, Object> map);
	
	int insertBoardCommentHate(Map<String, Object> map);
	
	int deleteBoardCommentHate(Map<String, Object> map);

	int selectBoardImageNo();

	void insertBoardImage(Map<String, Object> map);

	String selectImageFile(int fno);
}
