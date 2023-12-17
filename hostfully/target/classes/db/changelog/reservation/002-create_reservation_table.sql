CREATE TABLE RESERVATION (
    reservation_id VARCHAR(255) PRIMARY KEY,
    booker_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_of_adults INT NOT NULL,
    number_of_children INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_RESERVATION_BOOKER
        FOREIGN KEY (booker_id)
            REFERENCES BOOKER(booker_id)
)