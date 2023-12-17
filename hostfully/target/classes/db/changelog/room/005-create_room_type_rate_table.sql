CREATE TABLE ROOM_TYPE_RATE (
    room_type_id VARCHAR(255),
    date DATE,
    rate_adult DECIMAL(10, 2) NOT NULL,
    rate_children DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (room_type_id, date)
);