INSERT INTO ADDRESS
    (address_id, street, city, state, postal_code, country, created_at, updated_at)
VALUES
    ('6f00570a-275f-450d-843b-768ceae2ee89', '123 Main St', 'Cityville', 'Stateville', '12345', 'Countryland', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO HOTEL
    (hotel_id, name, address_id, created_at, updated_at)
VALUES
    ('87914f21-5ae3-443a-95fb-22961b9c0b54', 'Grand Hotel', '6f00570a-275f-450d-843b-768ceae2ee89', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);