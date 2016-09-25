package com.birdseye;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class Main
{

	public static void main( String[] args )
	{
		System.out.println("Starting server...");
		
		/* routes */
		get("/groups", (req, res) -> {
			return "TODO";
		});
		post("/join", (req, res) -> {
			return "TODO";
		});
		post("/create", (req, res) -> {
			return "TODO";
		});
		post("/ping", (req, res) -> {
			return "TODO";
		});

	}

	static Object joinGroup(Request req, Response res){
		return req.body();
	}
}
