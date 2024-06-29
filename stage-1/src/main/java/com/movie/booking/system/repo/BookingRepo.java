package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Booking;
import com.movie.booking.system.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {


    List<Booking> findAllByUserUserId(long userId);
}
