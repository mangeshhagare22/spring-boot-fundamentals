package com.mangesh.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mangesh.lms.model.Book;
import com.mangesh.lms.repos.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/")
	public String listBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());

		return "books";

	}

	@GetMapping("/book/new")
	public String showCreateForm(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "create_book";

	}

	
	@PostMapping("/books")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/";
		
	}

}
