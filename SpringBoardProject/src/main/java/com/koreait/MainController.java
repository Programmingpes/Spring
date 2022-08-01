package com.koreait;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.FileDTO;
import com.koreait.dto.MemberDTO;
import com.koreait.service.BoardService;
import com.koreait.service.MemberService;
import com.koreait.vo.PaggingVO;

@Controller
public class MainController {
	private BoardService boardService;
	private MemberService memberService;

	public MainController(BoardService boardService, MemberService memberService) {
		this.boardService = boardService;
		this.memberService = memberService;
	}
	
	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo",defaultValue = "1") int pageNo,
			Model model) {
//		System.out.println(pageNo);
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("list", list);
		
		//페이징 처리
		int count = boardService.selectAllCount();
		PaggingVO vo = new PaggingVO(count,pageNo,10,5);
		model.addAttribute("pagging", vo);
		
		return "main";
	}
	
	@RequestMapping("boardView.do")
	public String boardView(int bno, Model model, HttpSession session) {
		BoardDTO dto = boardService.selectBoardDTO(bno);
		List<FileDTO> flist = boardService.selectFileList(bno);
		List<BoardCommentDTO> comment = boardService.selectCommentDTO(bno);
		// 게시글 조회수 증가
		HashSet<Integer> set = (HashSet<Integer>) session.getAttribute("bno_history");
		if (set == null)
			set = new HashSet<Integer>();

		if (set.add(bno))
			boardService.addBoardCount(bno);
		session.setAttribute("bno_history", set);
		model.addAttribute("board", dto);
		model.addAttribute("flist", flist);
		model.addAttribute("comment", comment);
		return "board_detail_view";
	}
	
	@RequestMapping("loginView.do")
	public String loginView() {
		return "login";
	}
	
	@RequestMapping("login.do")
	public String login(String id, String passwd, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);
		MemberDTO dto = memberService.login(map);
		
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getGradeNo());
		
		if(dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("grade", dto.getGradeNo());
			return "redirect:/";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/deleteBoard.do")
	public String boardDelete(int bno) {
		//파일 삭제
		//게시글에 올린 첨부 파일 목록을 전부 읽어옴
		List<FileDTO> list = boardService.selectFileList(bno);
		for(int i=0;i<list.size();i++) {
			File file = new File(list.get(i).getPath());
			try {
				if(file.delete())
					System.out.println("파일 삭제 성공");;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		//게시글을 삭제
		boardService.deleteBoard(bno);
		return "redirect:/";
	}
	
	@RequestMapping("/insertComment.do")
	public void insertComment(int bno, String writer, 
			String comment, HttpServletResponse response) {
		BoardCommentDTO dto = new BoardCommentDTO(bno, comment, writer);
		int result = boardService.insertBoardComment(dto);
		try {
			response.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/plusLikeHate.do")
	public void plusLikeHate(int bno, int mode, 
			HttpSession session, HttpServletResponse response) {
		int result = 0;
		String id = (String) session.getAttribute("id");
		if(mode == 0) {
			//좋아요
			result = boardService.insertBoardLike(bno,id);
		}else {
			//싫어요
			result = boardService.insertBoardHate(bno,id);
		}
		try {
			response.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		return "board_write_view";
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite(BoardDTO dto, MultipartHttpServletRequest request) throws FileUploadException {
		boardService.boardWrite(dto);
		
		int bno = boardService.selectBno();
		System.out.println(bno);
		
		
		File userRoot = new File("c:\\fileupload\\");
		String encoding = "utf-8";
		
		if(!userRoot.exists())
			userRoot.mkdirs();
		
			List<MultipartFile> filelist = request.getFiles("file");
			int i = 1;
			for(MultipartFile f : filelist) {
				String originalFileName = f.getOriginalFilename();
				if(f.getSize() == 0) continue;
				File uploadFile = new File("c:\\fileupload\\" + "\\" +originalFileName);
				boardService.insertFile(new FileDTO(uploadFile, bno, i));
				i++;
				try {
					//실제로 전송
					f.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("fileDown.do")
	public void fileDown(int fno, int bno, HttpServletResponse response) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		System.out.println(bno);
		System.out.println(fno);
		map.put("bno", bno);
		map.put("fno", fno);
		FileDTO dto = boardService.selectFile(map);
		String path = "c:\\fileupload\\" + dto.getFileName();
		
		File file = new File(path);
		
		String fileName;
		try {
			fileName = URLEncoder.encode(dto.getFileName(), "utf-8");
			response.setHeader("Content-Disposition", "attachement;fileName=" + fileName);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLength((int)file.length());
			
			FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024*1024]; // 1메가
			
			while(true) {
				int size = fis.read(buffer); // 파일 사이즈를 확인, 1메가 단위로 끊어서
				if(size == -1) break;
				
				bos.write(buffer, 0, size);
				bos.flush();
			}
				fis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}