package com.interview.application.controller.api.model.request;

import com.interview.application.controller.api.validator.annotation.DateRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@DateRange(start = "checkinDate", end = "checkoutDate", message = "Checkin date must be before checkout date")
public class CreateBookingRequest {

    @Valid
    @NotNull(message = "must be informed")
    private Booker booker;

    @NotNull(message = "must be informed")
    @Future(message = "must be in the future")
    private LocalDate checkinDate;

    @NotNull(message = "must be informed")
    @Future(message = "must be in the future")
    private LocalDate checkoutDate;

    @NotNull(message = "must be informed")
    @Min(value = 0, message = "must equal 0 or greater than 0")
    private Long numberOfAdults;

    @NotNull(message = "must be informed")
    @Min(value = 0, message = "must equal 0 or greater than 0")
    private Long numberOfChildren;

    @NotEmpty(message = "must be informed")
    private List<@Valid RoomBooking> roomBookings;

    @Getter
    @Setter
    @Builder
    public static class Booker {

        @NotEmpty(message = "must be informed")
        private String firstName;

        @NotEmpty(message = "must be informed")
        private String lastName;

        @NotEmpty(message = "must be informed")
        @Email(message = "should be valid")
        private String email;

        @Valid
        @NotNull(message = "must be informed")
        private Address address;

        @Getter
        @Setter
        @Builder
        public static class Address {

            @NotEmpty(message = "must be informed")
            private String street;

            @NotEmpty(message = "must be informed")
            private String city;

            @NotEmpty(message = "must be informed")
            private String state;

            @NotEmpty(message = "must be informed")
            private String postalCode;

            @NotEmpty(message = "must be informed")
            private String country;
        }
    }

    @Getter
    @Setter
    @Builder
    public static class RoomBooking {

        @NotEmpty(message = "must be informed")
        private String roomId;

        @NotEmpty(message = "must be informed")
        private String guestName;

        @NotEmpty(message = "must be informed")
        private String guestEmail;
    }

}
