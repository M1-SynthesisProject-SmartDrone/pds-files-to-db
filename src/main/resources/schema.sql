CREATE TABLE altitude (
	id SERIAL PRIMARY KEY,
	timestamp BIGINT NOT NULL,
	altitude_monotonic real NOT NULL,
	altitude_amsl real NOT NULL,
	altitude_local real NOT NULL,
	altitude_relative real NOT NULL,
	altitude_terrain real NOT NULL,
	bottom_clearance real NOT NULL
);

CREATE TABLE highres (
	id SERIAL PRIMARY KEY,
	timestamp BIGINT NOT NULL,
	xacc real NOT NULL,
	yacc real NOT NULL,
	zacc real NOT NULL,
	xgyro real NOT NULL,
	ygyro real NOT NULL,
	zgyro real NOT NULL,
	xmag real NOT NULL,
	ymag real NOT NULL,
	zmag real NOT NULL,
	abs_pressure real NOT NULL,
	diff_pressure real NOT NULL,
	pressure_alt real NOT NULL,
	temperature real NOT NULL
);

CREATE TABLE battery (
	id SERIAL PRIMARY KEY,
	timestamp BIGINT NOT NULL,
	current_consumed INT NOT NULL,
	energy_consumed INT NOT NULL,
	temperature INT NOT NULL,
	current_battery INT NOT NULL,
	battery_remaining INT NOT NULL,
	time_remaining INT NOT NULL
);