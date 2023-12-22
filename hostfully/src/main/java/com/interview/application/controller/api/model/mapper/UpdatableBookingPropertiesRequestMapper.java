package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.request.UpdatableBookingPropertiesRequest;
import com.interview.application.domain.UpdatableBookingProperties;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdatableBookingPropertiesRequestMapper {

    UpdatableBookingProperties map(UpdatableBookingPropertiesRequest request);

}
