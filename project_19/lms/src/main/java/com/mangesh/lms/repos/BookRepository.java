package com.mangesh.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.lms.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

}
