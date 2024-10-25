package com.mangesh.lms.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.lms.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {

	List<Borrowing>  findByReturnDate(Date date);

}
