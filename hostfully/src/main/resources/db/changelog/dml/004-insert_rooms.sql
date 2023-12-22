INSERT INTO ROOM (room_id, hotel_id, floor, number, max_occupancy, is_available, room_type_id, created_at, updated_at)
SELECT
    UUID() AS room_id,
    '87914f21-5ae3-443a-95fb-22961b9c0b54' AS hotel_id,
    'Floor ' || (floor(random() * 10) + 1)::int AS floor,
        'Room ' || (floor(random() * 1000) + 1)::int AS number,
        (floor(random() * 4) + 1)::int AS max_occupancy,
        CASE WHEN random() < 0.5 THEN true ELSE false END AS is_available,
    (SELECT room_type_id FROM room_type ORDER BY random() LIMIT 1) AS room_type_id,
    CURRENT_TIMESTAMP AS created_at,
    CURRENT_TIMESTAMP AS updated_at
FROM
    generate_series(1, 50) AS seq;