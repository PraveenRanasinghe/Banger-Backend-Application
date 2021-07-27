package com.banger.backend.Repositary;

import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
    List<Booking> findBookingsByUser(User user);
}
