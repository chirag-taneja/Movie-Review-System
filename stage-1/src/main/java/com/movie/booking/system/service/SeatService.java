package com.movie.booking.system.service;

import com.movie.booking.system.dto.SeatDto;
import com.movie.booking.system.entity.Seat;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.repo.SeatRepo;
import com.movie.booking.system.repo.ShowRepo;
import com.movie.booking.system.util.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatService {

    public final SeatRepo seatRepo;
    public final ShowRepo showRepo;

    public ResponseEntity<Object> saveSeatForShow(SeatDto seatDto) {
        Set<Seat> seatSet=new LinkedHashSet<>();
        Optional<Show> byId = showRepo.findById(seatDto.showId());
        for (int i=1;i<seatDto.totalSeatInRow();i++)
        {
            Seat seat = Seat.builder()
                    .seatNumber(seatDto.rowChar()+""+i)
                    .show(byId.get())
                    .status(SeatStatus.AVAILABLE)
                    .build();
            seatSet.add(seat);
        }
        List<Seat> seats = seatRepo.saveAll(seatSet);
        return ResponseEntity.status(HttpStatus.CREATED).body("Seats created successfully for that show");
    }
}
