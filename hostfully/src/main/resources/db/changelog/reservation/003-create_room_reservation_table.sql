CREATE TABLE ROOM_RESERVATION (
    reservation_id VARCHAR(255) NOT NULL,
    room_id INT NOT NULL,
    guest_name VARCHAR(200) NOT NULL,
    guest_email VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (reservation_id, room_id),
    CONSTRAINT FK_RR_RESERVATION
        FOREIGN KEY (reservation_id)
            REFERENCES RESERVATION(reservation_id),
    CONSTRAINT FK_RESERVATION_ROOM
        FOREIGN KEY (room_id)
            REFERENCES ROOM(room_id)
)