package com.bookmanagement.google.assistant.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bookmanagement.google.assistant.constant.IntentUtil;
import com.bookmanagement.google.assistant.model.Book;
import com.bookmanagement.google.assistant.service.BookManagementInterface;
import com.bookmanagement.google.assistant.util.BookUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

@Service
public class BookService extends DialogflowApp{

	public static final String NOT_FOUND_MESSAGE = "I haven't found the specific author. Please try again";
	final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	@Qualifier(value = "BookAndAuthorService")
	private BookManagementInterface bookManagementService;
	
	@ForIntent(IntentUtil.LIST_AUTHORS_BOOK)
	public ActionResponse findAuthorBook(ActionRequest request) {
		logger.info("Execute intend "+ IntentUtil.LIST_AUTHORS_BOOK);

		
		final Map<String, Object> parameter = (Map<String, Object>)request.getParameter("person");
		final String authorName = parameter.get("name").toString();
		final StringBuilder response = new StringBuilder();
		if(authorName != null) {
			// List of Books in database written by author
			final List<Book> bookByAuthor = bookManagementService.getBookByAuthor(authorName, 0, 100);

			// Start creating the response
			response.append(BookUtil.getRandomListOfBookMessages());

			// Join the Author in authorMessage
			response.append(bookByAuthor.stream().map(e -> e.getTitle()).collect(Collectors.joining(", ")));
			response.append(". ");
			response.append(BookUtil.getRandomBookSelectedMessages());

			// Build the ActionResponse and add the 'response' to it

			
		} else {
			response.append(NOT_FOUND_MESSAGE);
		}
		
		final ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
		final ActionResponse actionResponse = responseBuilder.build();
		logger.info(new JSONObject(actionResponse.toJson()).toString(4));

		return actionResponse;

	}
	
	@ForIntent(IntentUtil.GET_LIST_AUTHORS_BOOK)
	public ActionResponse findBookDetails(ActionRequest request) {
		logger.info("Execute intend "+ IntentUtil.GET_LIST_AUTHORS_BOOK);
		
		@SuppressWarnings("unchecked")
		final Map<String, Object> parameter = (Map<String, Object>)request.getParameter("person");
		final String authorName = parameter.get("name").toString();
		logger.info("<<<<  Author Name >>>> "+authorName);
		
		final String bookTitle = request.getParameter("bookTitle").toString();
		logger.info("<<<<  Author Name >>>> "+bookTitle);
		final StringBuilder response = new StringBuilder();
		if(authorName != null) {
			// List of Books in database written by author
			final Book bookByTitle = bookManagementService.getBookByTitle(bookTitle);

			// Start creating the response
			//response.append(BookUtil.getRandomListOfBookMessages());

			// Join the Author in authorMessage
			response.append(bookByTitle.getTitle());
			response.append(". ");
			//response.append(BookUtil.getRandomBookSelectedMessages());

			// Build the ActionResponse and add the 'response' to it

			
		} else {
			response.append(NOT_FOUND_MESSAGE);
		}
		
		final ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
		final ActionResponse actionResponse = responseBuilder.build();
		logger.info(new JSONObject(actionResponse.toJson()).toString(4));

		return actionResponse;

	}
}
