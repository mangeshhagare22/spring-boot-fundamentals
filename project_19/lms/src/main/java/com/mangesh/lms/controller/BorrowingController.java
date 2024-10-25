package com.mangesh.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangesh.lms.repos.BookRepository;
import com.mangesh.lms.repos.BorrowingRepository;
import com.mangesh.lms.repos.MemberRepository;
import com.mangesh.lms.service.BorrowingService;

@Controller
public class BorrowingController {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BorrowingRepository borrowingRepository;
	@Autowired
	BorrowingService borrowingService;

	@GetMapping("/borrow")
	public String showBorrowForm(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("members", memberRepository.findAll());
		return "borrow_book";
	}

	@PostMapping("/borrow")
	public String borrowBook(@RequestParam Long memberId, @RequestParam Long bookId, Model model) {
		borrowingService.borrowBook(bookId, memberId);
		model.addAttribute("message", "Book Borrowed Sucessfully!");

		return "borrow_success";
	}

	@GetMapping("/return")
	public String showReturnForm(Model model) {
		model.addAttribute("borrowers", borrowingRepository.findByReturnDate(null));
		model.addAttribute("members", memberRepository.findAll());
		return "return_book";
	}

	@PostMapping("/return")
	public String returnBook(Long borrowingId, Model model) {

		borrowingService.returnBook(borrowingId);
		model.addAttribute("message", "Book Returned Sucessfully!");
		return "return_success";
	}
}
