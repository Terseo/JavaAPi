package com.unosquare;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class ApiCore { 
	
	public static ValidatableResponse PostLogin(String filePath, String url) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parserLogin = new JSONParser();
		String login = new File(filePath).getAbsolutePath();
		Object objLogin = (JSONObject) parserLogin.parse(new FileReader(login));
		RestAssured.baseURI = url;

		ValidatableResponse response = given().when().body(objLogin).post().then();
		
		return response;
	}
}
