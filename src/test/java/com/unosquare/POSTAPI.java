package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.ValidatableResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;


public class POSTAPI {
	@BeforeSuite
	public void beforeMethod() {
		
	}

	@Test
	public void PostLogin() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/login";
		
		ValidatableResponse responseLogin = ApiCore.PostLogin("src\\test\\java\\com\\unosquare\\Login.json", url);

		Reporter.log("Reponse Body " + responseLogin.extract().path(""));
		Reporter.log("Reponse Code " + responseLogin.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseLogin.assertThat().statusCode(400).assertThat().body("error", equalTo("Missing email or username"));
	}

}
