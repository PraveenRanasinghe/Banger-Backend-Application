package com.banger.backend.Repositary;

import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
    List<Booking> findBookingsByUser(User user);

    Booking findBookingByBookingId(Integer id);

    List<Booking> findBookingsByUserEmail(String email);

    List<Booking> findBookingByPickupTimeAndReturnTime(LocalDateTime pickupTime, LocalDateTime returnTime);

    List<Booking> findByBookingStatus(String bookingStatus);
}
