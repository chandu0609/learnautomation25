package com.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.gorest.api.UserPayLoad;
import com.gorest.api.base.BaseAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoTestUserTest extends BaseAPI{
	
	
	@Test (priority = 0)
	public void CreateUserAPI() {
		UserPayLoad user = new UserPayLoad("Chandra", "Chandra" + System.currentTimeMillis() + "@sample.com",
                "male", "active");
		Response createResponse = given()
				                 .header("Authorization","Bearer "+ getToken())
				                 .contentType(ContentType.JSON)
				                 .body(user)
				                 .log().all()
				              .when()
				                 .post("/users")
				              .then()
				                 .log().all()
				                 .statusCode(201)
				                 .time(lessThan(2000L))
				                 .body("name", equalTo(user.getName()))
				                 .body("email", equalTo(user.getEmail()))
                                 .extract().response();
				                 
				               
		String nameCreated = createResponse.path("name");	
		Assert.assertEquals(nameCreated, user.getName());
		
		int userId = createResponse.path("id");
		System.out.println("New User ID " + userId);
		 
		Response getResponse = given()
				               .header("Authorization", "Bearer " + getToken())
				               .log().all()
				             .when()
				               .get("users/" + userId)
				             .then()
				               .statusCode(200)
				               .log().all()
				               .body("email", equalTo(user.getEmail()))
				               .extract().response();
          
		Assert.assertEquals(getResponse.path("id").toString(), String.valueOf(userId));
		Assert.assertEquals(getResponse.path("name"), user.getName());
		Assert.assertEquals(getResponse.path("email"), user.getEmail());
		Assert.assertEquals(getResponse.path("gender"), user.getGender());
		Assert.assertEquals(getResponse.path("status"), user.getStatus());
				             
		
	}


	
	
			          

}
