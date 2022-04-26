package com.bookmanagement.google.assistant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookmanagement.google.assistant.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

	public Page<Book> findAll(Pageable pageable);

	public Page<Book> findByAuthorAuthorname(String authorname, Pageable pageable);
	
	public Book findByTitle(String title);
}
