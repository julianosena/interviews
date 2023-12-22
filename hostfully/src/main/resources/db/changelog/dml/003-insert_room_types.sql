INSERT INTO ROOM_TYPE (room_type_id, name, description, rate_adult, rate_children, created_at, updated_at)
VALUES
    (UUID(), 'Standard Room', 'Cozy and comfortable standard room', 100.00, 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID(), 'Deluxe Room', 'Spacious deluxe room with additional amenities', 150.00, 75.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID(), 'Suite', 'Luxurious suite with separate living area', 200.00, 100.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID(), 'Family Suite', 'Ideal for families with spacious accommodations', 250.00, 125.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID(), 'Ocean View Room', 'Room with a beautiful view of the ocean', 180.00, 90.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID(), 'Business Suite', 'Designed for business travelers with workspace', 220.00, 110.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);