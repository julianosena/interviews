package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Room;
import com.interview.application.gateway.FindRoomByIdGateway;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FindRoomByIdH2Gateway implements FindRoomByIdGateway {

    @Override
    public List<Room> execute(List<UUID> ids) {
        return Collections.emptyList();
    }

}
