package com.interview.application.usecase;

import com.interview.application.domain.Room;
import com.interview.application.domain.RoomFilter;
import com.interview.application.gateway.FindRoomsByFilterGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindRoomsByFilterUseCase {

    private final FindRoomsByFilterGateway gateway;

    public List<Room> execute(final RoomFilter filter){
        return gateway.execute(filter);
    }

}
