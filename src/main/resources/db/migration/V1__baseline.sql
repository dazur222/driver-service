CREATE TABLE drivers (
    id            VARCHAR(64) NOT NULL,
    full_name     VARCHAR(120),
    safety_score  NUMERIC(5,2),
    assignment_id VARCHAR(64),
    vehicle_id    VARCHAR(64),
    CONSTRAINT drivers_pkey PRIMARY KEY (id),
    CONSTRAINT drivers_assignment_id_key UNIQUE (assignment_id),
    CONSTRAINT drivers_vehicle_id_key UNIQUE (vehicle_id)
);

CREATE TABLE driver_credentials (
    driver_id          VARCHAR(64) NOT NULL,
    license_number     VARCHAR(60) NOT NULL,
    license_category   VARCHAR(40),
    license_issued_on  DATE,
    license_expires_on DATE,
    CONSTRAINT driver_credentials_pkey PRIMARY KEY (driver_id),
    CONSTRAINT driver_credentials_license_number_key UNIQUE (license_number),
    CONSTRAINT driver_credentials_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES drivers (id)
);

CREATE TABLE driver_certifications (
    driver_id     VARCHAR(64) NOT NULL,
    "position"    INTEGER     NOT NULL,
    certification VARCHAR(80) NOT NULL,
    CONSTRAINT driver_certifications_pkey PRIMARY KEY (driver_id, "position"),
    CONSTRAINT driver_certifications_position_check CHECK ("position" >= 0),
    CONSTRAINT driver_certifications_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES drivers (id)
);
