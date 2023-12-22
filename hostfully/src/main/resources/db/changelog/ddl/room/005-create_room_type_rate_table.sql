CREATE TABLE ROOM_TYPE_RATE (
    room_type_id VARCHAR(255),
    date DATE,
    rate_adult DECIMAL(10, 2) NOT NULL,
    rate_children DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (room_type_id, date)
);