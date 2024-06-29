package com.movie.booking.system.controller;

import com.movie.booking.system.dto.SeatDto;
import com.movie.booking.system.dto.ShowDto;
import com.movie.booking.system.service.SeatService;
import com.movie.booking.system.util.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {

    public final SeatService seatService;
    @PostMapping
    public ResponseEntity<Object> saveSeatForShow(@RequestBody SeatDto seatDto)
    {
        return  seatService.saveSeatForShow(seatDto);
    }
}
