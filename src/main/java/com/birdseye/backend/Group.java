package com.birdseye;

import java.util.Map;
import java.util.HashMap;


public class Group
{
	public static Map<Integer, Group> groups = new HashMap<Integer, Group>();

	private final Map<Integer, User> users;
	private final int teams;
	private final int groupId;

	public Group(int teams){
		this.teams = teams;
		this.users = new HashMap<Integer, User>();

		for(int i = 0; i < groups.size() + 1; i++){
			if(!groups.containsKey(i)){
				this.groupId = i;
				break;
			}
		}
	}

	public int getGroupId(){
		return groupId;
	}

	public void addUser(User user){
		users.put(User.getKey(), User);
	}

	public void destroy(){
		groups.remove(groupId);
	}
}

