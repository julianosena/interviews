CREATE TABLE ROOM (
    room_id VARCHAR(255) PRIMARY KEY,
    hotel_id VARCHAR(255) NOT NULL,
    room_type_id VARCHAR(255) NOT NULL,
    floor VARCHAR(50) NOT NULL,
    number VARCHAR(50) NOT NULL,
    max_occupancy INT NOT NULL,
    is_available BOOLEAN,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT FK_ROOM_HOTEL
        FOREIGN KEY (hotel_id)
            REFERENCES HOTEL(hotel_id),
    CONSTRAINT FK_R_ROOM_TYPE
        FOREIGN KEY (room_type_id)
            REFERENCES ROOM_TYPE(room_type_id)
);