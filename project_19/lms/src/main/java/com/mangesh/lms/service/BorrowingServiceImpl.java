package com.mangesh.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.lms.model.Borrowing;
import com.mangesh.lms.repos.BookRepository;
import com.mangesh.lms.repos.BorrowingRepository;
import com.mangesh.lms.repos.MemberRepository;

@Service
public class BorrowingServiceImpl implements BorrowingService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BorrowingRepository borrowingRepository;

	@Override
	public Borrowing borrowBook(Long bookId, Long memberId) {
		Borrowing borrowing = new Borrowing();
		borrowing.setBook(bookRepository.findById(bookId).get());
		borrowing.setMember(memberRepository.findById(memberId).get());
		borrowing.setBorrowedDate(new Date());
		return borrowingRepository.save(borrowing);
	}

	@Override
	public Borrowing returnBook(Long borrowingId) {
		Borrowing borrowing = borrowingRepository.findById(borrowingId).get();
		borrowing.setReturnDate(new Date());
		return borrowingRepository.save(borrowing);
	}

}
