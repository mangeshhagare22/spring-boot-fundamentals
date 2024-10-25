package com.mangesh.lms.service;

import com.mangesh.lms.model.Borrowing;

public interface BorrowingService {
	
	Borrowing borrowBook(Long bookId,Long memberId);
	Borrowing returnBook(Long borrowingId);

}
