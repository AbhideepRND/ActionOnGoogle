package com.bookmanagement.google.assistant.service.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bookmanagement.google.assistant.service.ActionService;

@Service(value = "ActionOnGoogleRequestServiceImpl")
public class ActionOnGoogleRequestServiceImpl implements ActionService {

	/**
	 * { "responseId": "c303cf0a-bae3-421e-83b5-1037d7689e95-21554733",
	 * "queryResult": { "queryText": "List_Author", "parameters": {},
	 * "allRequiredParamsPresent": true, "fulfillmentMessages": [ { "text": {
	 * "text": [ "" ] } } ], "intent": { "name":
	 * "projects/book-management-850d0/agent/intents/cf1afcbe-a32d-4e98-893e-11ee7dc4a9f7",
	 * "displayName": "List_Authors" }, "intentDetectionConfidence": 0.5690331,
	 * "languageCode": "en", "sentimentAnalysisResult": { "queryTextSentiment": {
	 * "score": 0.3, "magnitude": 0.3 } } } }
	 */

	@Override
	public String getIntentName(String body) throws Exception {

		// Convert the above raw request in json format
		final JSONObject jsonObject = new JSONObject(body);

		final String intentName = jsonObject.getJSONObject("queryResult") // get queryResult from above json
				.getJSONObject("intent") // get intent from above queryResult
				.get("displayName").toString();

		return intentName;
	}

}
