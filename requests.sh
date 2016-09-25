#!/bin/bash

curl -X POST http://birdseye-backend.herokuapp.com:8080/groups
curl -H "Content-Type: application/json" -X POST -d '{"name": "Team Lee", "teams": 6}' http://birdseye-backend.herokuapp.com:8080/create
curl -H "Content-Type: application/json" -X POST -d '{"group_id": 0, "team": 0}' http://birdseye-backend.herokuapp.com:8080/join
curl -H "Content-Type: application/json" -X POST -d '{"id":0,"group_id":0, "latitude": 41, "longitude": -70, "locations": true}' http://birdseye-backend.herokuapp.com:8080/ping
