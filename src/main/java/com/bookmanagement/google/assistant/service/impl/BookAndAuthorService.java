package com.bookmanagement.google.assistant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanagement.google.assistant.exception.BusinessException;
import com.bookmanagement.google.assistant.model.Author;
import com.bookmanagement.google.assistant.model.Book;
import com.bookmanagement.google.assistant.repository.AuthorRepository;
import com.bookmanagement.google.assistant.repository.BookRepository;
import com.bookmanagement.google.assistant.service.BookManagementInterface;
import com.sun.xml.bind.unmarshaller.Messages;

@Service(value="BookAndAuthorService")
public class BookAndAuthorService implements BookManagementInterface {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private BookRepository bookRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Author addAuthor(final Author author) {

		final Author findByAuthorname = authorRepo.findByAuthorname(author.getAuthorname());

		if (findByAuthorname != null) {
			throw new BusinessException(Messages.format("Author {} already exist with system", author.getAuthorname()));
		}

		authorRepo.save(author);

		return author;
	}

	@Override
	public List<Author> getAllAuthor(final Integer page, final Integer size) {
		final PageRequest of = PageRequest.of(page, size);
		return authorRepo.findAll(of).toList();
	}

	@Override
	public Author getAuthor(String authorName) {
		return authorRepo.findByAuthorname(authorName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book addBook(final Book book) {

		/* First check the author then check the book exist */

		if (book == null) {
			throw new BusinessException(Messages.format("Book details is empty"));
		}

		if (book.getAuthor() == null || book.getAuthor().getAuthorname() == null) {
			throw new BusinessException(Messages.format("Author of Book {} is empty", book.getTitle()));
		}

		final Author findByAuthorname = authorRepo.findByAuthorname(book.getAuthor().getAuthorname());

		if (findByAuthorname == null) {
			throw new BusinessException(Messages.format("Author {} for book {} is not present in system",
					book.getAuthor().getAuthorname(), book.getTitle()));
		}

		final Book findByTitle = bookRepo.findByTitle(book.getTitle());

		if (findByTitle != null) {
			throw new BusinessException(Messages.format("Book {} is not present in system", book.getTitle()));
		}

		book.setAuthor(findByAuthorname);

		bookRepo.save(book);

		return book;
	}

	@Override
	public List<Book> getAllBooks(Integer page, Integer size) {
		final PageRequest of = PageRequest.of(page, size);
		return bookRepo.findAll(of).toList();
	}

	@Override
	public List<Book> getBookByAuthor(String authorName, Integer page, Integer size) {
		final PageRequest of = PageRequest.of(page, size);
		return bookRepo.findByAuthorAuthorname(authorName, of).toList();
	}
	
	@Override
	public Book getBookByTitle(String bookTitle) {
		return bookRepo.findByTitle(bookTitle);
	}
	
	

}
