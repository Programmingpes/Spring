package com.example;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberDTO;
import com.example.service.MemberSerive;

@Controller
public class MainController {
	private MemberSerive service;

	public MainController(MemberSerive service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main(Model model) {
		List<MemberDTO> list = service.selectAllMember();
		model.addAttribute("list", list);
		return "main";
	}
	
	@RequestMapping("/register.do")
	public String insertMember(MemberDTO dto) {
		service.insertMember(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/delete.do")
	public String deleteMemeber(String id) {
		service.deleteMember(id);
		return "redirect:/";
	}
	@RequestMapping("/updateView.do")
	public String updateView(String id, HttpServletRequest request) {
		MemberDTO dto = service.selectMember(id);
		request.setAttribute("dto", dto);
		return "updateView";
	}
	
	@RequestMapping("/update.do")
	public String updateMember(MemberDTO dto) {
		service.updateMember(dto);
		return "redirect:/";
	}
}
