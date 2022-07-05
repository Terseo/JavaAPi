package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import java.util.Collections;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

public class GETAPI {
  
  @Test
  public void getApiUser() {
	  
	  ValidatableResponse responseUser = ApiCore.Get("https://reqres.in/api/users/2");
	  responseUser
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.id", equalTo(2))
	  		.assertThat().body("data.email", equalTo("janet.weaver@reqres.in"))
	  		.assertThat().body("data.first_name", equalTo("Janet"))
	  		.assertThat().body("data.last_name", equalTo("Weaver"))
	  		.assertThat().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
	  
	  Reporter.log("Reponse Code " + responseUser.extract().statusCode());
}
  
  @Test
  public void getApiInvalidUser() {

	  	ValidatableResponse responseUser = ApiCore.Get("https://reqres.in/api/users/23");
	  	  responseUser
	  		.assertThat().statusCode(404)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("", equalTo(Collections.emptyMap()));
	  
	  	Reporter.log("Reponse Code " + responseUser.extract().statusCode());
}
  
  @Test
  public void getApiUnknown() {


	  	ValidatableResponse responseUser = ApiCore.Get("https://reqres.in/api/unknown/2");
	  	  responseUser
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.id", equalTo(2))
	  		.assertThat().body("data.name", equalTo("fuchsia rose"))
	  		.assertThat().body("data.year", equalTo(2001))
	  		.assertThat().body("data.color", equalTo("#C74375"))
	  		.assertThat().body("data.pantone_value", equalTo("17-2031"));
	  
	  	Reporter.log("Reponse Code " + responseUser.extract().statusCode());
}
  
  @Test
  public void getApiInvalidUnknown() {

	  	ValidatableResponse responseUser = ApiCore.Get("https://reqres.in/api/unknown/23");
	  	  responseUser
	  		.assertThat().statusCode(404)
	  		.assertThat().contentType(ContentType.JSON)
	  	    .assertThat().body("", equalTo(Collections.emptyMap()));
	  
	  	Reporter.log("Reponse Code " + responseUser.extract().statusCode());
}

  @BeforeMethod
  public void beforeMethod() {
  }

}
