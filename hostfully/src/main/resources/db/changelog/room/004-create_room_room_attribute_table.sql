CREATE TABLE ROOM_ROOM_ATTRIBUTE (
    room_id VARCHAR(255) NOT NULL,
    room_attribute_id VARCHAR(255) NOT NULL,
    CONSTRAINT FK_RRA_ROOM
        FOREIGN KEY (room_id)
            REFERENCES ROOM(room_id),
    CONSTRAINT FK_RRA_ROOM_ATTRIBUTE
            FOREIGN KEY (room_attribute_id)
                REFERENCES ROOM_ATTRIBUTE(room_attribute_id)
);