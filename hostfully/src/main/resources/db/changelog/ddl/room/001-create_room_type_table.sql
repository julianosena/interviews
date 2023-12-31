CREATE TABLE ROOM_TYPE (
    room_type_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(200),
    description TEXT,
    rate_adult DECIMAL(10, 2) NOT NULL,
    rate_children DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);