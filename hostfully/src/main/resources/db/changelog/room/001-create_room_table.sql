CREATE TABLE ROOM (
    room_id INT PRIMARY KEY,
    property_id INT NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    max_occupancy INT NOT NULL,
    rate_adult DECIMAL(10, 2) NOT NULL,
    rate_children DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_property FOREIGN KEY (property_id) REFERENCES Property(property_id)
);