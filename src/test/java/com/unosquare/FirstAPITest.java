package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

public class FirstAPITest {
  
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
