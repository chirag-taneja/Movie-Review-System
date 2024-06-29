package com.movie.booking.system.vo;

import java.util.Date;

public record MovieVo(Long movieId, String title, String genre, String duration, String language, Date releaseDate, boolean activeFlag) {
}
