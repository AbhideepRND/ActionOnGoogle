package com.bookmanagement.google.assistant.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bookmanagement.google.assistant.constant.IntentUtil;
import com.bookmanagement.google.assistant.model.Author;
import com.bookmanagement.google.assistant.service.BookManagementInterface;
import com.bookmanagement.google.assistant.util.AuthorUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

@Service
public class AuthorService extends DialogflowApp {

	final Logger logger = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	@Qualifier(value = "BookAndAuthorService")
	private BookManagementInterface bookManagementService;

	@ForIntent(IntentUtil.LIST_AUTHOR)
	public ActionResponse findAll(ActionRequest request) {
		logger.info("Execute intend " + IntentUtil.LIST_AUTHOR);

		// List of Author in database
		final List<Author> allAuthor = bookManagementService.getAllAuthor(0, 100);

		// Start creating the response
		final StringBuilder response = new StringBuilder(AuthorUtil.getRandomListOfAuthorMessages());

		// Join the Author in authorMessage
		response.append(allAuthor.stream().map(e -> e.getAuthorname()).collect(Collectors.joining(", ")));
		response.append(". ");
		response.append(AuthorUtil.getRandomAuthorSelectedMessages());

		// Build the ActionResponse and add the 'response' to it

		final ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
		final ActionResponse actionResponse = responseBuilder.build();
		logger.info(new JSONObject(actionResponse.toJson()).toString(4));

		return actionResponse;

	}

}
