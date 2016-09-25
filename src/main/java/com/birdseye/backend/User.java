package com.birdseye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;

public class User
{
	private final int id;
	private final int team;
	
	private double longitude;
	private double latitude;
	private boolean initialized;

	public User(int id, int team){
		this.id = id;
		this.team = team;
		this.initialized = false;
	}

	public int getId(){
		return id;
	}

	public int getTeam(){
		return team;
	}

	public double getLongitude(){
		return longitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLongitude(double l){
		initialized = true;
		longitude = l;
	}

	public void setLatitude(double l){
		initialized = true;
		latitude = l;
	}

	public boolean isInitialized(){
		return initialized;
	}

	public JSONObject toJson(){
		try{
			JSONObject toReturn = new JSONObject();

			toReturn.put("id", id);
			toReturn.put("latitude", latitude);
			toReturn.put("longitude", longitude);
			toReturn.put("team", team);

			return toReturn;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

