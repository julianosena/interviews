CREATE TABLE ROOM_BOOKING (
    booking_id VARCHAR(255),
    room_id VARCHAR(255) NOT NULL,
    guest_name VARCHAR(200) NOT NULL,
    guest_email VARCHAR(200) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (booking_id, room_id),
    CONSTRAINT FK_RB_BOOKING
        FOREIGN KEY (booking_id)
            REFERENCES BOOKING(booking_id),
    CONSTRAINT FK_BOOKING_ROOM
        FOREIGN KEY (room_id)
            REFERENCES ROOM(room_id)
)