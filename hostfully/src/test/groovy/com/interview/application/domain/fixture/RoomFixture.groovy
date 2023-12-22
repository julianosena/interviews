package com.interview.application.domain.fixture

import com.interview.application.domain.Hotel
import com.interview.application.domain.Room
import com.interview.application.domain.RoomType
import org.spockframework.util.Assert

import java.time.Instant

class RoomFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                floor: "Second",
                number: "19X",
                maxOccupancy : 3,
                isAvailable : true,
                attributes : [],
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return Room.builder()
                .id(values.id as UUID)
                .hotel(values.hotel as Hotel)
                .type(values.type as RoomType)
                .floor(values.floor as String)
                .number(values.number as String)
                .attributes(values.attributes as List)
                .maxOccupancy(values.maxOccupancy as Long)
                .available(values.isAvailable as Boolean)
                .attributes(values.attributes as List)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }

    static List<Room> list(Hotel hotel, def parameters = [:]){
        Assert.that(null != hotel, "Hotel can't be null")
        Assert.that(null != hotel.id, "Hotel must be existent")
        def defaultValues = [
                totalAmountRooms : 100,
                floorsAmount: 5
        ]

        def values = defaultValues + parameters

        def roomInstances = []
        def floors = values.floorsAmount
        def roomsPerFloor = (values.totalAmountRooms / floors) as Integer
        def random = new Random()

        def realisticRoomTypes = [
                RoomTypeFixture.create([
                        id : UUID.randomUUID(),
                        name : "Standard",
                        description : "Standard Room",
                        rateAdult : new BigDecimal("100.00"),
                        rateChildren : new BigDecimal("50.00"),
                        createdAt: Instant.now(),
                        updatedAt: Instant.now()
                ]),
                RoomTypeFixture.create([
                        id : UUID.randomUUID(),
                        name : "Deluxe",
                        description : "Deluxe Room",
                        rateAdult : new BigDecimal("150.00"),
                        rateChildren : new BigDecimal("75.00"),
                        createdAt: Instant.now(),
                        updatedAt: Instant.now()
                ])
        ]

        def realisticAttributes = [
                "Square Meters",
                "View",
                "Air Conditioning",
                "Private Bathroom",
                "Flat-screen TV",
                "Soundproof",
                "Coffee Machine",
                "Free WiFi"
        ]

        for (int floorNumber = 1; floorNumber <= floors; floorNumber++) {
            for (int roomNumber = 1; roomNumber <= roomsPerFloor; roomNumber++) {
                def attributeCount = random.nextInt(5) + 1 // Randomly choose 1 to 5 attributes
                def attributes = []

                for (int i = 0; i < attributeCount; i++) {
                    def attributeId = UUID.randomUUID()
                    def attributeDescription = realisticAttributes[random.nextInt(realisticAttributes.size())]
                    def roomAttribute = RoomAttributeFixture.create([
                            id : attributeId,
                            description : attributeDescription
                    ])
                    attributes.add(roomAttribute)
                }

                def roomType = realisticRoomTypes[random.nextInt(realisticRoomTypes.size())]

                def roomParameters = [
                        id: UUID.randomUUID(),
                        hotel : hotel,
                        floor: "Floor-${floorNumber}",
                        type: roomType,
                        number: "Room-${(floorNumber - 1) * roomsPerFloor + roomNumber}",
                        maxOccupancy: (roomNumber % 5) + 1,
                        isAvailable: random.nextBoolean(),
                        attributes: attributes,
                        createdAt: Instant.now(),
                        updatedAt: Instant.now()
                ]

                roomInstances.add(create(roomParameters))
            }
        }

        return roomInstances
    }
}

