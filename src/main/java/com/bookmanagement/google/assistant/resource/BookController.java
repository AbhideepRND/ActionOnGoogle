package com.bookmanagement.google.assistant.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.google.assistant.model.Book;
import com.bookmanagement.google.assistant.service.BookManagementInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Book Service" )
@RestController
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	@Qualifier(value = "BookAndAuthorService")
	private BookManagementInterface bookManagementService;

	@ApiOperation(value="All Book", httpMethod="GET", notes="Get all books", produces="application/json")
	@GetMapping(path="/getAllBook", produces="application/json")
	public List<Book> getAllBookList(@RequestParam("pageNo") Integer page, @RequestParam("pageSize") Integer size ) {
		return bookManagementService.getAllBooks(page, size);
	}
	
	@ApiOperation(value="Book by Author", httpMethod="GET", notes="Get all books by Author", produces="application/json")
	@GetMapping(path="/getAllBook/{author}", produces="application/json")
	public List<Book> getAllBookList(@PathVariable("author") String author, @RequestParam("pageNo") Integer page, @RequestParam("pageSize") Integer size ) {
		return bookManagementService.getBookByAuthor(author, page, size);
	}
	
	@ApiOperation(value="Add Book", httpMethod="PUT", notes="Add books", consumes="application/json", produces="application/json")
	@PutMapping(path="/addBook", consumes="application/json", produces="application/json")
	public Book addBook(@RequestBody Book book) {
			return bookManagementService.addBook(book);
	}
	
}
