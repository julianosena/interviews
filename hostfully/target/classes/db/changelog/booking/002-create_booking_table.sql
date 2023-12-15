CREATE TABLE Booking (
    booking_id INT PRIMARY KEY,
    booker_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booker
        FOREIGN KEY (booker_id)
            REFERENCES Booker(booker_id)
)