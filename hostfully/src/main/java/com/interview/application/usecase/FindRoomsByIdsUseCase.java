package com.interview.application.usecase;

import com.interview.application.domain.Room;
import com.interview.application.gateway.FindRoomByIdsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class FindRoomsByIdsUseCase {

    private final FindRoomByIdsGateway findRoomByIdsGateway;

    public List<Room> execute(final List<UUID> ids){
        return findRoomByIdsGateway.execute(ids);
    }

    public List<Room> execute(final List<UUID> ids, final Predicate<Room> predicate){
        return this.execute(ids)
                .stream()
                .filter(predicate)
                .toList();
    }

}
