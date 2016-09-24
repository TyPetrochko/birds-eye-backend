# Bird's Eye (.io)
## Backend


API

## Get groups

Get a list of groups currently in play.

- URL:
	- `/groups`
- Method:
	- `GET`
- Response object
```json
{"groups": [
			{"name":"Team Alex", "group_id":12, "num_teams": 6},
			{"name":"Team Tyty", "group_id":4, "num_teams": 1}
	]
}
```

## Join groups

Join a group. This also tells the server to assign the sender a unique ID. Once you get a response, immediately start pinging the server with your location.

- URL:
	- `/join`
- Method:
	- `POST`
- Data params. `team` is optional.
	- `{"group_id":6, "team": 2}`
- Response object
	- `{"status":"ok", "id": 54, "team": 2}`
- List of statuses (maybe more to come)
	- `ok`
	- `bad request`

## Create a group

Create a group with a certain number of teams. If you don't want to play with teams, set `teams` to 1.

- URL:
	- `/create`
- Method:
	- `POST`
- Data params. `team` is optional.
	- `{"teams": 2}`
- Response object:
	- `{"group_id": 4}`

## Ping

Update the server with your current location.

- URL:
	- `/ping`
- Method:
	- `POST`
- JSON data params. `speed` and `direction` are optional, but don't include only one!
	- `{"id": 12, "group_id": 6, "latitude": 41.613032, "longitude": -70.970479, "precision": 10, "speed": 1.5, "direction": 91.3, "locations": true}`
- Response object if locations set to `true`
```json
{"status": "ok", "locations": [
		{"latitude": 41.613032, "longitude": -70.970479, "friendly": true},
		{"latitude": 41.512046, "longitude": -71.072379, "friendly": false},
		{"latitude": 41.603001, "longitude": -70.970479, "friendly": false}
	]
}
```
- Response object if locations set to `false`
	- `{"status": "ok"`
- List of statuses (maybe more to come)
	- `ok`
	- `bad request`

