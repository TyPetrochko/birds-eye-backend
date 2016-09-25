package com.birdseye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User
{
	private final int id;
	private final int team;
	
	public User(int id, int team){
		this.id = id;
		this.team = team;
	}

	public int getId(){
		return id;
	}

	public int getTeam(){
		return team;
	}
}

