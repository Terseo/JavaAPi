package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

public class PUTAPI {
	@Test
	public void PutUsers() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/users/2";

		ValidatableResponse responseUser = ApiCore.Post(".\\json\\UpdateUsers.json", url);

		Reporter.log("Reponse Body " + responseUser.extract().path(""));
		Reporter.log("Reponse Code " + responseUser.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseUser.assertThat().statusCode(201)
					 .assertThat().body("name", equalTo("morpheus"))
					 .assertThat().body("job", equalTo("zion resident"))
					 .assertThat().body("updatedAt", is(not(empty())));
	}
  @BeforeMethod
  public void beforeMethod() {
  }

}
