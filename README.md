# Bird's Eye (.io)
## Backend


API

## Get groups

## Join groups

## Ping

Update the server with your current location.
	- URL:
		`to be determined...`
	- Method:
		`POST`
	- Data params. `team` is optional, as are `speed` and `direction`
		`{"id": 12, "group_id": 6, "latitude": 41.613032, "longitude": -70.970479, "precision": 10, "speed": 1.5, "direction": 91.3, "locations": true}`
	- Response object if locations set to `true`
		`{"status": "ok", "locations": [
				{"latitude": 41.613032, "longitude": -70.970479, "friendly": true}
				{"latitude": 41.512046, "longitude": -71.072379, "friendly": false}
				{"latitude": 41.603001, "longitude": -70.970479, "friendly": false}
				]}`
	- Response object if locations set to `false`
		`{"status": "ok"`
	- List of responses
		- `ok`
		- `b

