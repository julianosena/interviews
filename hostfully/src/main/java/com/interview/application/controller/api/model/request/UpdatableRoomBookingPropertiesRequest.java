package com.interview.application.controller.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = {"roomId", "bookingId"})
public class UpdatableRoomBookingPropertiesRequest {

    @NotNull(message = "must be informed")
    private UUID roomId;

    @NotNull(message = "must be informed")
    private UUID bookingId;

    private String guestName;
    private String guestEmail;

}
