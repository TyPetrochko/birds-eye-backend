package com.birdseye;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class Main
{

	public static void main( String[] args )
	{
		System.out.println("Starting server...");
		
		// routes:
		get("/groups", (req, res) -> "Get a list of groups");
		post("/join", (req, res) -> joinGroup(req, res));
		post("/create", (req, res) -> "Create a group");
		post("/ping", (req, res) -> "Update the server with our whereabouts");

	}

	static Object joinGroup(Request req, Response res){
		return req.body();
	}
}
