package com.wipro.witaas.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class ResponseCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Validate if Add place API is working as expected
		
		// given - all input details
		// when - Submit the api
		// Then - validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
//		given().queryParam("key", "qaclick123").header(headerName, headerValue, additionalHeaderValues)
		System.out.println("Naveen");

	}

}
