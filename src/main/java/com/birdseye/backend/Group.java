package com.birdseye;

import org.json.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;


public class Group
{
	/* A map of group IDs to groups */
	public static Map<Integer, Group> groups = new ConcurrentHashMap<Integer, Group>();

	/* A map of user IDs to users */
	private final Map<Integer, User> users;
	private final int teams;
	private final String name; 
	private int groupId;

	/* Get a list of all group */
	public static List<Group> getGroups(){
		List<Group> toReturn = new ArrayList<>();

		for(Group g : groups.values()){
			toReturn.add(g);
		}
		return toReturn;
	}

	public Group(String name, int teams){
		if(teams < 1){
			teams = 1;
			System.err.println("Made a group with " + teams + " teams");
		}

		this.teams = teams;
		this.name = name;
		this.users = new ConcurrentHashMap<Integer, User>();

		this.groupId = 0;
		for(int i = 0; i < groups.size() + 1; i++){
			if(!groups.containsKey(i)){
				this.groupId = i;
				groups.put(i, this);
				return;
			}
		}

		/* should never get here */
		System.err.println("Couldn't find a proper ID for our team");
		System.exit(1);
	}

	public int getGroupId(){
		return groupId;
	}

	public int getNumTeams(){
		return teams;
	}

	public int addUser(int team){
		for(int i = 0; i < users.size() + 1; i++){
			if(!users.containsKey(i)){
				User u = new User(i, team);
				users.put(i, u);
				return i;
			}
		}

		System.err.println("Couldn't find a proper ID for the user to add");
		System.exit(1);
		return -1;
	}

	public List<User> getUsers(){
		ArrayList<User> toReturn = new ArrayList<>();
		for(User u: users.values()){
			toReturn.add(u);
		}

		return toReturn;
	}

	public void destroy(){
		groups.remove(groupId);
	}

	public boolean hasUser(int id){
		return users.containsKey(id);
	}

	public void updateUser(int id, double latitude, double longitude){
		if(!hasUser(id)){
			System.err.println("Didn't check hasUser before updateUser!");
			System.exit(1);
		}

		User u = users.get(id);

		u.setLatitude(latitude);
		u.setLongitude(longitude);
	}

	public JSONObject toJson(){
		try{
			JSONObject toReturn = new JSONObject();

			toReturn.put("name", name);
			toReturn.put("group_id", groupId);
			toReturn.put("num_teams", teams);

			return toReturn;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

