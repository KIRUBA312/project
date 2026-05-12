package com.example.apigateway.util;

import com.example.apigateway.constants.AppConstants;
import com.example.apigateway.exception.InvalidApiKeyException;

public class ApiKeyValidator {

	public static void validate(String apikey) {
		if(apikey == null || !apikey.equals(AppConstants.API_KEY)) {
			throw new InvalidApiKeyException("Invalid API KEY");
		}
	}
}
