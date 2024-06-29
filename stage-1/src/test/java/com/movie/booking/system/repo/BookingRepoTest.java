package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Booking;
import com.movie.booking.system.entity.Seat;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.entity.User;
import com.movie.booking.system.util.SeatStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingRepoTest {
    
    BookingRepo bookingRepo;
    UserRepo userRepo ;
    ShowRepo showRepo;
    SeatRepo seatRepo;

    @Autowired
    public BookingRepoTest(BookingRepo bookingRepo, UserRepo userRepo, ShowRepo showRepo, SeatRepo seatRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
        this.showRepo = showRepo;
        this.seatRepo = seatRepo;
    }
//in this we will create a booking and send seats selected by user and

    @Test
    public void saveBooking()
    {
        //get user by id 
        Optional<User> byId = userRepo.findById(1L);

        // get show for which show this booking for 
        Show show = showRepo.findById(3L).get();
        int noOfSeatsBooked=3;
        //get set of object selected by user 
        //lets assume user selected 4,5,6
        Set<Seat> seats =new LinkedHashSet<>();
//        for (int i=0;i<noOfSeatsBooked;i++)
//        {
//            
//        }
//
//        
        seats.add(seatRepo.findById(4L).get());
        seats.add(seatRepo.findById(5L).get());
        seats.add(seatRepo.findById(6L).get());
        
        Booking booking = Booking.builder()
                .user(byId.get())
                .show(show)
                .noOfSeatsBooked(noOfSeatsBooked)
                .seats(seats).bookingTime(new Date())
                .build();
        seats.forEach(i->i.setStatus(SeatStatus.BOOKED));
        List<Seat> seats1 = seatRepo.saveAll(seats);
        Booking save = bookingRepo.save(booking);
    }
    
}