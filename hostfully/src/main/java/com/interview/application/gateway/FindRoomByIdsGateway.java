package com.interview.application.gateway;

import com.interview.application.domain.Room;

import java.util.List;
import java.util.UUID;

public interface FindRoomByIdsGateway {

    List<Room> execute(List<UUID> ids);

}
