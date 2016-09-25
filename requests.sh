#!/bin/bash

curl -X POST http://fd25b0f9.ngrok.io/groups
curl -H "Content-Type: application/json" -X POST -d '{"name": "Team Lee", "teams": 6}' http://fd25b0f9.ngrok.io/create
curl -H "Content-Type: application/json" -X POST -d '{"group_id": 0, "team": 0}' http://fd25b0f9.ngrok.io/join
curl -H "Content-Type: application/json" -X POST -d '{"id":0,"group_id":0, "latitude": 41, "longitude": -70, "locations": true}' http://fd25b0f9.ngrok.io/ping
