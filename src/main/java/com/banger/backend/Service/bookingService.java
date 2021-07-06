package com.banger.backend.Service;

import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.Equipment;
import com.banger.backend.Repositary.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class bookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public Booking getBookingById(Integer bookingId) {
        Optional<Booking> bookings = bookingRepo.findById(bookingId);
        Booking booking = null;
        if (bookings.isPresent()) {
            booking = bookings.get();
        }
        return booking;
    }

    public List<Booking> getAllBookingsToList() {
        return bookingRepo.findAll();
    }

    public Booking makeBooking(bookingDTO dto) {
        Booking booking = new Booking();
        booking.setBookingId(dto.getBookingId());
        booking.setPickupDate(dto.getPickupDate());
        booking.setReturnDate(dto.getReturnDate());
        booking.setPickupTime(LocalTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalTime.parse(dto.getReturnTime()));
        booking.setVehicle(dto.getVehicle());
        booking.setEquipments(dto.getEquipments());
        booking.setUser(dto.getUser());

        return bookingRepo.save(booking);
    }

//    public Booking addEquipmentToBooking(bookingDTO dto) {
//        Booking booking = new Booking();
//        booking.setEquipments(dto.getEquipments());
//        return bookingRepo.save(booking);
//    }

    public Booking updateBooking(bookingDTO dto) {
        Booking booking = new Booking();
        booking.setBookingId(dto.getBookingId());
        booking.setPickupDate(dto.getPickupDate());
        booking.setReturnDate(dto.getReturnDate());
        booking.setPickupTime(LocalTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalTime.parse(dto.getReturnTime()));
        booking.setVehicle(dto.getVehicle());
        booking.setEquipments(dto.getEquipments());
        booking.setUser(dto.getUser());

        return bookingRepo.save(booking);
    }

    public void removeBookings(Booking booking){
        bookingRepo.delete(booking);
    }
}
