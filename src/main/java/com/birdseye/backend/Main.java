package com.birdseye;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import org.json.*;

import java.util.List;

public class Main
{

	public static void main( String[] args )
	{
		int p = getHerokuAssignedPort();
		port(p);
		System.out.println("Starting server on port " + p);
		
		/* get groups */
		post("/groups", (req, res) -> {
			List<Group> groups = Group.getGroups();
			JSONObject obj = new JSONObject();
			JSONArray arr = new JSONArray();

			for(Group g : groups){
				arr.put(g.toJson());
			}
			obj.put("groups", arr);
			return obj;
		});

		/* join a group */
		post("/join", (req, res) -> {
			JSONObject toReturn = new JSONObject();
			JSONObject requestJson = new JSONObject(req.body());

			String [] requiredFields = {"group_id", "team"};

			for(String field : requiredFields){
				if(!requestJson.has(field)){
					System.err.println("Request missing field: " + field);
					return badRequest();
				}
			}

			int groupId = requestJson.getInt("group_id");
			int team = requestJson.getInt("team");

			// make sure that the team makes sense
			if(Group.groups.get(groupId).getNumTeams() <= team){
				System.err.println("Request had a team that was too high: ");
				System.err.println("\tGroupId: " 
						+ Group.groups.get(groupId).getNumTeams());
				System.err.println("\tTeam: " + team);
				return badRequest();
			}

			int id = Group.groups.get(groupId).addUser(team);

			toReturn.put("id", id);
			toReturn.put("status", "ok");
			return toReturn;
		});

		/* create a group */
		post("/create", (req, res) -> {
			System.out.println("Incoming request:");
			System.out.println(req.body());
			JSONObject toReturn = new JSONObject();
			JSONObject requestJson = new JSONObject(req.body());

			String [] requiredFields = {"name", "teams"};
			for(String field : requiredFields){
				if(!requestJson.has(field)){
					System.err.println("Request missing field: " + field);
					return badRequest();
				}
			}

			String name = requestJson.getString("name");
			int teams = requestJson.getInt("teams");

			Group g = new Group(name, teams);

			toReturn.put("group_id", g.getGroupId());

			System.out.println("Outgoing response:");
			System.out.println(toReturn.toString());
			return toReturn.toString();
		});

		/* send a ping */
		post("/ping", (req, res) -> {
			JSONObject toReturn = new JSONObject();
			JSONObject requestJson = new JSONObject(req.body());
			JSONArray arr = new JSONArray();

			/* check required fields */
			String [] requiredFields = {
				"group_id", "id", "latitude", "longitude","precision", "speed", 
				"direction", "locations"
			};

			for(String field : requiredFields){
				if(!requestJson.has(field)){
					System.err.println("Request missing field: " + field);
					return badRequest();
				}
			}

			/* read in fields */
			int id = requestJson.getInt("id");
			int groupId = requestJson.getInt("group_id");
			int lat = requestJson.getInt("latitude");
			int lon = requestJson.getInt("longitude");
			boolean showLocations = requestJson.getBoolean("locations");

			/* make sure the request makes sense*/
			if(!Group.groups.containsKey(groupId) || 
					!Group.groups.get(groupId).hasUser(id)){
				System.err.println("Received bad request:");
				System.err.println(req.body());
				return badRequest();
			}

			Group.groups.get(groupId).updateUser(id, lat, lon);

			toReturn.put("status", "ok");

			/* send back locations of nearby players */
			if(showLocations){
				toReturn.put("locations", arr);
				/* build array of users */
				for(User u : Group.groups.get(groupId).getUsers()){
					if(u.isInitialized() && u.getId() != id) arr.put(u.toJson());
				}
			}

			return toReturn.toString();
		});

	}

	static String badRequest(){
		try{
			JSONObject toReturn = new JSONObject();
			toReturn.put("status", "bad request");
			return toReturn.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
static int getHerokuAssignedPort() {
	ProcessBuilder processBuilder = new ProcessBuilder();
	if (processBuilder.environment().get("PORT") != null) {
		return Integer.parseInt(processBuilder.environment().get("PORT"));
	}
	return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
