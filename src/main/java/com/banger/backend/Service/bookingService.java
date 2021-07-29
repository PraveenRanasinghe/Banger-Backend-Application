package com.banger.backend.Service;

import com.banger.backend.DTO.acceptBookingDTO;
import com.banger.backend.DTO.acceptUserDTO;
import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.DTO.userDTO;
import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.Equipment;
import com.banger.backend.Entity.User;
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


    public List<bookingDTO> getAllBookingsToList() {
         List<Booking> bookingList =  bookingRepo.findAll();
         List<bookingDTO> dtoList = new ArrayList<>();

         for(Booking bookings: bookingList){
             bookingDTO dto= new bookingDTO();
             dto.setEmail(bookings.getUser().getEmail());
             dto.setBookingId(bookings.getBookingId());
             dto.setReturnTime(bookings.getReturnTime().toString());
             dto.setPickupTime(bookings.getPickupTime().toString());
             dto.setEquipments(bookings.getEquipments());
             dto.setVehicle(bookings.getVehicle());

             dtoList.add(dto);
         }
         return dtoList;
    }



    public void makeBooking(bookingDTO dto) throws Exception {
        Booking booking = new Booking();
//        List<Equipment> equipmentList= new ArrayList<>();

        booking.setVehicle(vehicleRepo.getOne(dto.getVehicleId()));
        booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));

//        for(Equipment equipments:dto.getEquipments()){
//            equipmentList.add(equipmentRepo.findById(equipments.getEquipmentId()).get());
//        }
//        booking.setEquipments(equipmentList);

        System.out.println("Hello " + dto.getPickupTime());
        List<Booking> bookingList= bookingRepo.findBookingByPickupTimeAndReturnTime(LocalDateTime.parse(dto.getPickupTime()),
                LocalDateTime.parse(dto.getReturnTime()));


        for(Booking bookingInfo: bookingList){
            if((LocalDateTime.parse((dto.getPickupTime())).isAfter(bookingInfo.getPickupTime()))
                    && (LocalDateTime.parse((dto.getPickupTime())).isBefore(bookingInfo.getReturnTime()))){
                throw new Exception("You cannot Make the Booking at this moment.Because this vehicle is Already booked for selected Time Period!");
            }
            else if((LocalDateTime.parse((dto.getPickupTime())).isAfter(bookingInfo.getPickupTime()))
                    && (LocalDateTime.parse((dto.getReturnTime())).isBefore(bookingInfo.getReturnTime()))){
                throw new Exception("You cannot Make the Booking at this moment.Because this vehicle is Already booked for selected Time Period!");
            }
        }
        booking.setUser(userRepo.getOne(dto.getEmail()));
        booking.setBookingStatus("Pending");
        booking.setIsLateReturn("False");

        bookingRepo.save(booking);
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


    public List<bookingDTO> getAllPendingBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Pending")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public List<bookingDTO> getAllAcceptedBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Accepted")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public List<bookingDTO> getAllRejectedBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Rejected")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public Booking acceptBooking(acceptBookingDTO dto) {
        Booking booking = bookingRepo.findBookingByBookingId(dto.getBookingId());
        booking.setBookingStatus("Accepted");
        return bookingRepo.save(booking);
    }

    public Booking rejectBooking(acceptBookingDTO dto){
        Booking booking = bookingRepo.findBookingByBookingId(dto.getBookingId());
        booking.setBookingStatus("Rejected");
        return bookingRepo.save(booking);
    }

}
