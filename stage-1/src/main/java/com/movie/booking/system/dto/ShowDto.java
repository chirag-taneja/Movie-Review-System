package com.movie.booking.system.dto;

import java.util.Date;

public record ShowDto(long movieId, long theatreId, String showTime,int screenNo) {
}
