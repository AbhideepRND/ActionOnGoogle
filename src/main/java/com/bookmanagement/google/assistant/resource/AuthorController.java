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

import com.bookmanagement.google.assistant.model.Author;
import com.bookmanagement.google.assistant.service.BookManagementInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Author Service")
@RestController
@RequestMapping(path = "/author")
public class AuthorController {

	@Autowired
	@Qualifier(value = "BookAndAuthorService")
	private BookManagementInterface bookManagementService;

	@ApiOperation(value = "All Author", httpMethod = "GET", notes = "Get all Author", produces = "application/json")
	@GetMapping(path = "/getAllAuthor", produces = "application/json")
	public List<Author> getAllAuthorList(@ApiParam("pageNo") @RequestParam("pageNo") Integer page, @ApiParam("pageNo") @RequestParam("pageSize") Integer size) {
		return bookManagementService.getAllAuthor(page, size);
	}

	@ApiOperation(value = "Find Author", httpMethod = "GET", notes = "Find Author", produces = "application/json")
	@GetMapping(path = "/getAuthor/{author}", produces = "application/json")
	public Author getAuthor(@PathVariable("author") String author) {
		return bookManagementService.getAuthor(author);
	}

	@ApiOperation(value = "Add Author", httpMethod = "PUT", notes = "Add Author", consumes = "application/json", produces = "application/json")
	@PutMapping(path = "/addAuthor", consumes = "application/json", produces = "application/json")
	public Author addBook(@RequestBody Author author) {
		return bookManagementService.addAuthor(author);
	}
}
