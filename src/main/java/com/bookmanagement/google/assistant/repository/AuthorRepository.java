package com.bookmanagement.google.assistant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookmanagement.google.assistant.model.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

	public Page<Author> findAll(Pageable pageable);

	public Author findByAuthorname(String authorname);
}
