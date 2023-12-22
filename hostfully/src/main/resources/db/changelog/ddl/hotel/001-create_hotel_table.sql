CREATE TABLE HOTEL (
    hotel_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT FK_H_ADDRESS
        FOREIGN KEY (address_id)
            REFERENCES ADDRESS(address_id)
);