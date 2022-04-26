package com.bookmanagement.google.assistant.resource;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.google.assistant.constant.IntentUtil;
import com.bookmanagement.google.assistant.service.ActionService;
import com.bookmanagement.google.assistant.service.impl.AuthorService;
import com.bookmanagement.google.assistant.service.impl.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Google Action Controller")
@RestController
@RequestMapping("/api/action/")
public class ActionController {

	final Logger logger = LoggerFactory.getLogger(ActionController.class);

	@Autowired
	@Qualifier(value = "ActionOnGoogleRequestServiceImpl")
	private ActionService actionService;

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bokService;

	@ApiOperation(value = "Google Post Action", httpMethod = "POST", notes = "Post Action", produces = "application/json")
	@PostMapping
	public ResponseEntity<?> executePostAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// get body from HttpServletRequest
		final String googleMessage = request.getReader().lines().collect(Collectors.joining());
		logger.info(new JSONObject(googleMessage).toString(4));

		// get intend name (from user request)
		try {
			final String intentName = actionService.getIntentName(googleMessage);

			if (IntentUtil.LIST_AUTHOR.equals(intentName)) {

				final String authorJsonResp = authorService.handleRequest(googleMessage, this.getHeaderMap(request))
						.get();
				return new ResponseEntity<String>(authorJsonResp, HttpStatus.OK);
				
			} else if (IntentUtil.LIST_AUTHORS_BOOK.equals(intentName)) {
				final String authorJsonResp = bokService.handleRequest(googleMessage, this.getHeaderMap(request))
						.get();
				return new ResponseEntity<String>(authorJsonResp, HttpStatus.OK);

			} else if (IntentUtil.GET_LIST_AUTHORS_BOOK.equals(intentName)) {
				final String authorJsonResp = bokService.handleRequest(googleMessage, this.getHeaderMap(request))
						.get();
				return new ResponseEntity<String>(authorJsonResp, HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("Request could not be processed", HttpStatus.OK);
			}

		} catch (Exception msg) {
			logger.error("Error " + msg.getMessage());
			return new ResponseEntity<String>("Could not process the request ", HttpStatus.OK);
		}

	}

	@ApiOperation(value = "Google Get Action", httpMethod = "GET", notes = "Get Action", produces = "application/json")
	@GetMapping
	public ResponseEntity<?> executeGetAction() {
		return new ResponseEntity<String>("ActionsController accepts only Post request from google Assistance",
				HttpStatus.OK);
	}

	// Construct map of headers that will be sent to our intents (business logic)
	private Map<String, String> getHeaderMap(HttpServletRequest request) {
		final Map<String, String> headerMap = new HashMap<String, String>();
		final Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			final String key = headerNames.nextElement();
			headerMap.put(key, request.getHeader(key));
		}
		return headerMap;
	}

}
