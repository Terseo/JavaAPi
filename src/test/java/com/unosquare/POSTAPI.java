package com.unosquare;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static io.restassured.RestAssured.*;

public class POSTAPI {
	@Test
	public void PostLogin() throws FileNotFoundException, IOException, ParseException {
		JSONParser parserLogin = new JSONParser();
		String login = new File("src\\test\\java\\com\\unosquare\\Login.json").getAbsolutePath();
		Object objLogin = (JSONObject) parserLogin.parse(new FileReader(login));
		RestAssured.baseURI = "https://reqres.in/api/login";

		ValidatableResponse responseLogin = given().when().body(objLogin).post().then();

		Reporter.log("Reponse Body " + responseLogin.extract().path(""));
		Reporter.log("Reponse Code " + responseLogin.extract().statusCode());
		Reporter.log("Json Body " + objLogin);
		Reporter.log("Request URL " + RestAssured.baseURI);

		responseLogin.assertThat().statusCode(400).assertThat().body("error", equalTo("Missing email or username"));
	}

	@Test
	public void PostUsers() throws FileNotFoundException, IOException, ParseException {
		JSONParser parserUser = new JSONParser();
		String users = new File("src\\test\\java\\com\\unosquare\\Users.json").getAbsolutePath();
		Object objUsers = parserUser.parse(new FileReader(users));
		RestAssured.baseURI = "https://reqres.in/api/users";

		ValidatableResponse responseUsers = given().when().body(objUsers).post().then();

		Reporter.log("Reponse Body " + responseUsers.extract().path(""));
		Reporter.log("Reponse Code " + responseUsers.extract().statusCode());
		Reporter.log("Json Body " + objUsers);
		Reporter.log("Request URL " + RestAssured.baseURI);

		responseUsers.assertThat().statusCode(201).assertThat().body("id", is(not(empty()))).assertThat()
				.body("createdAt", is(not(empty())));
	}

	@BeforeMethod
	public void beforeMethod() {
	}

}
