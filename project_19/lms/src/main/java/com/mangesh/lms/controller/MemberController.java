package com.mangesh.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mangesh.lms.model.Member;
import com.mangesh.lms.repos.MemberRepository;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/members")
	public String listMembers(Model model) {
		model.addAttribute("members", memberRepository.findAll());

		return "members";

	}

	@GetMapping("/member/new")
	public String showCreateForm(Model model) {
		Member member=new Member();
		model.addAttribute("member", member);
		return "create_member";

	}
 
	
	@PostMapping("/members") 
	public String saveMember(Member member) {
		memberRepository.save(member);
		return "redirect:/members";
		
	}
	
	@GetMapping("/member/{id}")
	public String showUpdateForm(@PathVariable("id") Long id,Model model) {
		
		Member member = memberRepository.findById(id).get();
		model.addAttribute("member", member);
		return "update_member";

	}
 
	
	@PostMapping("/member/update") 
	public String updateMember(Member member) {
		memberRepository.save(member);
		return "redirect:/members";
		
	}
	
	

}
