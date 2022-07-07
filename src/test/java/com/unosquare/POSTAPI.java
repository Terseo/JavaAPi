package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.ValidatableResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;

public class POSTAPI {
	@BeforeSuite
	public void beforeMethod() {

	}

	@Test
	public void PostLogin() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/login";

		ValidatableResponse responseLogin = ApiCore.Post(".\\json\\Login.json", url);

		Reporter.log("Reponse Body " + responseLogin.extract().path(""));
		Reporter.log("Reponse Code " + responseLogin.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseLogin.assertThat().statusCode(200)
					 .assertThat().body("token", is(not(empty())));
	}

	@Test
	public void PostInvalidLogin() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/login";

		ValidatableResponse responseLogin = ApiCore.Post(".\\json\\InvalidLogin.json", url);

		Reporter.log("Reponse Body " + responseLogin.extract().path(""));
		Reporter.log("Reponse Code " + responseLogin.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseLogin.assertThat().statusCode(400)
					 .assertThat().body("error", equalTo("Missing password"));
	}

	@Test
	public void PostUsers() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/users";

		ValidatableResponse responseUsers = ApiCore.Post(".\\json\\Users.json", url);

		Reporter.log("Reponse Body " + responseUsers.extract().path(""));
		Reporter.log("Reponse Code " + responseUsers.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseUsers.assertThat().statusCode(201)
					 .assertThat().body("id", is(not(empty())))
					 .assertThat().body("createdAt", is(not(empty())));
	}

	@Test
	public void PostRegister() throws FileNotFoundException, IOException, ParseException {
		String url = "https://reqres.in/api/register";

		ValidatableResponse responseUsers = ApiCore.Post(".\\json\\Register.json", url);

		Reporter.log("Reponse Body " + responseUsers.extract().path(""));
		Reporter.log("Reponse Code " + responseUsers.extract().statusCode());
		Reporter.log("Request URL " + url);

		responseUsers.assertThat().statusCode(200)
				     .assertThat().body("id", is(not(empty())))
				     .assertThat().body("token", is(not(empty())));
	}

}
