package com.interview.application.gateway;

import com.interview.application.domain.Room;
import com.interview.application.domain.RoomFilter;

import java.util.List;

public interface FindRoomsByFilterGateway {

    List<Room> execute(RoomFilter filter);

}
