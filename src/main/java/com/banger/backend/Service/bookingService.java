package com.banger.backend.Service;

import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.Equipment;
import com.banger.backend.Repositary.BookingRepo;
import com.banger.backend.Repositary.EquipmentRepo;
import com.banger.backend.Repositary.UserRepo;
import com.banger.backend.Repositary.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private UserRepo userRepo;


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
        List<Equipment> equipmentList= new ArrayList<>();

        booking.setVehicle(vehicleRepo.getOne(dto.getVehicleId()));
        booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));

//        for(Equipment equipments:dto.getEquipments()){
//            equipmentList.add(equipmentRepo.findById(equipments.getEquipmentId()).get());
//        }
//        booking.setEquipments(equipmentList);

        booking.setUser(userRepo.getOne(dto.getEmail()));
        booking.setBookingStatus("Pending");

        return bookingRepo.save(booking);
    }


    public Booking updateBooking(bookingDTO dto) {
        Booking booking = bookingRepo.findById(dto.getBookingId()).get();
        booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));
        booking.setVehicle(dto.getVehicle());
        booking.setEquipments(dto.getEquipments());
        booking.setUser(dto.getUser());

        return bookingRepo.save(booking);
    }

    public void removeBookings(Booking booking){
        bookingRepo.delete(booking);
    }
}
