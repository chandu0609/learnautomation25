package com.gorest.api.base;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.restassured.RestAssured;

public class BaseAPI {

	private String apiToken = "";
	
	@BeforeClass
	@Parameters({"baseuri","API_TOKEN"})
	public void setup(String baseuri, String apitoken) {
		RestAssured.baseURI = baseuri;
		apiToken = apitoken;
	}
	
	public String getToken() {
		return apiToken;
	}
}
