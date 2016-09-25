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

	private int groupId;

	public Group(int teams){
		this.teams = teams;
		this.users = new ConcurrentHashMap<Integer, User>();

		this.groupId = 0;
		for(int i = 0; i < groups.size() + 1; i++){
			if(!groups.containsKey(i)){
				this.groupId = i;
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

	public void addUser(int team){
		for(int i = 0; i < users.size() + 1; i++){
			User u = new User(i, team);
			users.put(i, u);
			return;
		}

		System.err.println("Couldn't find a proper ID for the user to add");
		System.exit(1);
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

	public String toJson(){
		return "";
	}
}

