package com.bookmanagement.google.assistant.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookUtil {

	// List of message for incoming request (List_Author_Book intent)
	public static final List<String> listOfBookMessages = new ArrayList<String>();

	// List of message sent as response (List_Author_Book intent)
	public static final List<String> listOfBookSelectedMessages = new ArrayList<String>();

	static {

		listOfBookMessages.add("Sure, I got this books in my list: ");
		listOfBookMessages.add("Ok, I found some books for you: ");
		listOfBookMessages.add("I found the following books: ");

		listOfBookSelectedMessages.add("Please a the book you want to know more details about ");
		listOfBookSelectedMessages.add("Please choose one of them to continue.");
		listOfBookSelectedMessages.add("I can provide book details if you select on of them.");
	}

	// create method for extracting a random message using by Google Assistant
	public static String getRandomListOfBookMessages() {
		final int nextInt = new Random().nextInt(listOfBookMessages.size());
		return listOfBookMessages.get(nextInt);
	}

	public static String getRandomBookSelectedMessages() {
		final int nextInt = new Random().nextInt(listOfBookSelectedMessages.size());
		return listOfBookSelectedMessages.get(nextInt);
	}
}
