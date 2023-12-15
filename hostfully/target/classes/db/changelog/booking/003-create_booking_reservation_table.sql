CREATE TABLE BookingReservation (
    booking_id INT NOT NULL,
    room_id INT NOT NULL,
    guest_name VARCHAR(200) NOT NULL,
    guest_email VARCHAR(200) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_of_adults INT NOT NULL,
    number_of_children INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (booking_id, room_id),
    CONSTRAINT fk_booking
        FOREIGN KEY (booking_id)
            REFERENCES Booking(booking_id),
    CONSTRAINT fk_room
        FOREIGN KEY (room_id)
            REFERENCES Room(room_id)
)