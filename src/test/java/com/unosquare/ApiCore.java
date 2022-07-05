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
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class ApiCore { 
	
	public static ValidatableResponse Post(String filePath, String url) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parserLogin = new JSONParser();
		String file = new File(filePath).getAbsolutePath();
		Object objLogin = (JSONObject) parserLogin.parse(new FileReader(file));
		RestAssured.baseURI = url;

		ValidatableResponse response = given().when().contentType(ContentType.JSON).body(objLogin).post().then();
		
		return response;
	}
	
	public static ValidatableResponse put(String filePath, String url) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parserLogin = new JSONParser();
		String file = new File(filePath).getAbsolutePath();
		Object objLogin = (JSONObject) parserLogin.parse(new FileReader(file));
		RestAssured.baseURI = url;

		ValidatableResponse response = given().when().contentType(ContentType.JSON).body(objLogin).put().then();
		
		return response;
	}
	
	public static ValidatableResponse Get(String url) {
		ValidatableResponse response = given().when().get(url).then();
		return response;
		
	}
}
