package com.movie.booking.system.dto;

import java.util.Set;

public record BookingDto(long userId, long showId, int noOfSeatsBooked, Set<Long> seatIdForBooking ) {
}
