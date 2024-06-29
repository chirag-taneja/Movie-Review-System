package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Seat;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.util.SeatStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatRepoTest {

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    ShowRepo showRepo;

    @Test
    public void SaveSeats()
    {
        char rowChar='A';
        int totalSeatInRow=16;
        Set<Seat> seatSet=new LinkedHashSet<>();
        Optional<Show> byId = showRepo.findById(3L);
        for (int i=1;i<totalSeatInRow;i++)
        {
            Seat seat = Seat.builder()
                    .seatNumber(rowChar+""+i)
                    .show(byId.get())
                    .status(SeatStatus.AVAILABLE)
                    .build();
            seatSet.add(seat);
        }
        List<Seat> seats = seatRepo.saveAll(seatSet);

    }

}