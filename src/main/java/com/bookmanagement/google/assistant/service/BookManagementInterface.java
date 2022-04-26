package com.bookmanagement.google.assistant.service;

import java.util.List;

import com.bookmanagement.google.assistant.model.Author;
import com.bookmanagement.google.assistant.model.Book;

public interface BookManagementInterface {

	/**
	 * This method will add Author of doesn't exist
	 * 
	 * @param author
	 * @return
	 */
	public Author addAuthor(final Author author);

	/**
	 * This method will return Author list.
	 * 
	 * @return
	 */
	public List<Author> getAllAuthor(final Integer page, final Integer size);

	/**
	 * This method will return Author
	 * 
	 * @return
	 */
	public Author getAuthor(final String authorName);

	/**
	 * This method will add the book against the author. First it will search the
	 * Author, if found then add the book in database.
	 * 
	 * @param author
	 * @param book
	 * @return
	 */
	public Book addBook(final Book book);

	/**
	 * This method will return book list.
	 * 
	 * @return
	 */
	public List<Book> getAllBooks(final Integer page, final Integer size);

	/**
	 * This method will return list of books
	 * 
	 * @return
	 */
	public List<Book> getBookByAuthor(final String authorName, final Integer page, final Integer size);
	
	
	public Book getBookByTitle(String bookTitle);

}
