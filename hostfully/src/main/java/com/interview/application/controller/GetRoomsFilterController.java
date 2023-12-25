package com.interview.application.controller;

import com.interview.application.controller.api.model.RoomApiModel;
import com.interview.application.controller.api.model.RoomQueryParameters;
import com.interview.application.controller.api.model.mapper.RoomApiModelMapper;
import com.interview.application.controller.api.model.mapper.RoomQueryParametersMapper;
import com.interview.application.domain.RoomFilter;
import com.interview.application.usecase.FindRoomsByFilterUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
@Tag(name = "Rooms Management API", description = "This API provides endpoints to perform read operations on rooms.")
public class GetRoomsFilterController {

    private final FindRoomsByFilterUseCase useCase;
    private final RoomQueryParametersMapper roomQueryParametersMapper;
    private final RoomApiModelMapper roomApiModelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomApiModel> execute(final RoomQueryParameters parameters){
        RoomFilter filter = roomQueryParametersMapper.map(parameters);
        return useCase.execute(filter).stream().map(roomApiModelMapper::map).toList();
    }

}
