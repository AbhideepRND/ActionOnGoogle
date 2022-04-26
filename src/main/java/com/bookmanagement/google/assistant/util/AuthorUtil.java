package com.bookmanagement.google.assistant.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AuthorUtil {

	// List of message for incoming request (List_Author intent)
	public static final List<String> listOfAuthorMessages = new ArrayList<String>();

	// List of message sent as response (List_Author intent)
	public static final List<String> listOfAuthorSelectedMessages = new ArrayList<String>();

	static {

		listOfAuthorMessages.add("Here is the list of Authors: ");
		listOfAuthorMessages.add("Sure, here is the list: ");
		listOfAuthorMessages.add("I found the following author: ");

		listOfAuthorSelectedMessages.add("Which one would you like to select?");
		listOfAuthorSelectedMessages.add("Please choose one of them to continue.");
		listOfAuthorSelectedMessages.add("I can provide list of books if you choose an author.");
	}

	// create method for extracting a random message using by Google Assistant
	public static String getRandomListOfAuthorMessages() {
		final int nextInt = new Random().nextInt(listOfAuthorMessages.size());
		return listOfAuthorMessages.get(nextInt);
	}

	public static String getRandomAuthorSelectedMessages() {
		final int nextInt = new Random().nextInt(listOfAuthorSelectedMessages.size());
		return listOfAuthorSelectedMessages.get(nextInt);
	}
}
