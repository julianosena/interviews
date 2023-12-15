CREATE TABLE BOOKER (
    booker_id INT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email DECIMAL(10, 2) NOT NULL,
    address_street VARCHAR(200) NOT NULL,
    address_city VARCHAR(200) NOT NULL,
    address_postal_code VARCHAR(200) NOT NULL,
    address_country VARCHAR(200) NOT NULL,
    address_phone VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)