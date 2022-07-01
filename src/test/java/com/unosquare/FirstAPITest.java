package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

public class FirstAPITest {
  @Test
  public void f() {
	  
	    RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Sucess 200 validation");
		
		response.then().body("data.first_name", equalTo("Janet"));
		Reporter.log(response.body().asString());
  }
  
  @Test
  public void f_reqres_in() {
	  
	  given()
	  .when()
	  	.get("https://reqres.in/api/users/2")
	  		.then()
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.id", equalTo(2))
	  		.assertThat().body("data.email", equalTo("janet.weaver@reqres.in"))
	  		.assertThat().body("data.first_name", equalTo("Janet"))
	  		.assertThat().body("data.last_name", equalTo("Weaver"))
	  		.assertThat().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
	  		
	  		.assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))
	  		.assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
	  
	  Reporter.log("Sucess 200 validation");
}
  @BeforeMethod
  public void beforeMethod() {
  }

}
