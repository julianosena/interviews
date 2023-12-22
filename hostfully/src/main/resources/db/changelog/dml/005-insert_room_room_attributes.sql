INSERT INTO ROOM_ROOM_ATTRIBUTE (room_id, room_attribute_id)
SELECT r.room_id,
       ra.room_attribute_id
FROM
    (SELECT room_id,

            (SELECT FLOOR(RAND() * 30) + 1) AS num_attributes
     FROM ROOM
     ORDER BY RAND()) r
        JOIN
    (SELECT room_attribute_id,
            ROW_NUMBER() OVER (
                             ORDER BY RAND()) AS rn
     FROM ROOM_ATTRIBUTE) ra ON ra.rn <= r.num_attributes;