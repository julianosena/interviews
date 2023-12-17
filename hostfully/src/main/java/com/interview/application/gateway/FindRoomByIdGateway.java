package com.interview.application.gateway;

import com.interview.application.domain.Room;

import java.util.List;
import java.util.UUID;

public interface FindRoomByIdGateway {

    List<Room> execute(List<UUID> ids);

}
