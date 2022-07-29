package com.koreait.mapper;


import java.util.List;

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
}
