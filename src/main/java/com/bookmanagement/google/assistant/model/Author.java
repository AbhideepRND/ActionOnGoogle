package com.bookmanagement.google.assistant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the AUTHOR database table.
 * 
 */
@Entity
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AUTH_ID")
	private int authId;

	private String authoraddress;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.DATE)
	private Date authorbirthdate;

	private String authorname;

	@JsonIgnore
	@OneToMany(mappedBy="author")
	private List<Book> books;

	public Author() {
	}

	@JsonIgnore
	public int getAuthId() {
		return this.authId;
	}

	@JsonIgnore
	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthoraddress() {
		return this.authoraddress;
	}

	public void setAuthoraddress(String authoraddress) {
		this.authoraddress = authoraddress;
	}

	public Date getAuthorbirthdate() {
		return this.authorbirthdate;
	}

	public void setAuthorbirthdate(Date authorbirthdate) {
		this.authorbirthdate = authorbirthdate;
	}

	public String getAuthorname() {
		return this.authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setAuthor(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setAuthor(null);

		return book;
	}

}