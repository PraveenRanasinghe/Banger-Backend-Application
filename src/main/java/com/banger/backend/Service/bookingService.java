package com.banger.backend.Service;

import com.banger.backend.DTO.*;
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

    @Autowired
    private emailService emailService;

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
         List<equipmentDTO> dtoListEquip= new ArrayList<>();


         for(Booking bookings: bookingList){
             bookingDTO dto= new bookingDTO();
             dto.setEmail(bookings.getUser().getEmail());
             dto.setBookingId(bookings.getBookingId());
             dto.setReturnTime(bookings.getReturnTime().toString());
             dto.setPickupTime(bookings.getPickupTime().toString());
//             dto.setEquipments(bookings.getEquipments());
             dto.setVehicle(bookings.getVehicle());

             dtoList.add(dto);
         }
         return dtoList;
    }

    public List<bookingDTO> getBookingsByUserEmail(String email){
        List<Booking> bookingList = bookingRepo.findBookingsByUserEmail(email);
        List<bookingDTO> dtoList= new ArrayList<>();

        for(Booking bookings : bookingList){
            if(bookings.getBookingStatus().equals("Collected")){
                bookingDTO dto = new bookingDTO();
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());
                dto.setBookingStatus(bookings.getBookingStatus());

                dtoList.add(dto);
            }

        }
        return dtoList;
    }

    public List<bookingDTO> getCompletedBookingsByUserEmail(String email){
        List<Booking> bookingList = bookingRepo.findBookingsByUserEmail(email);
        List<bookingDTO> dtoList= new ArrayList<>();

        for(Booking bookings : bookingList){
            if(bookings.getBookingStatus().equals("Completed")){
                bookingDTO dto = new bookingDTO();
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());
                dto.setBookingStatus(bookings.getBookingStatus());

                dtoList.add(dto);
            }

        }
        return dtoList;
    }




    public void makeBooking(bookingDTO dto) throws Exception {
        Booking booking = new Booking();
        List<Equipment> equipmentList= new ArrayList<>();

        booking.setVehicle(vehicleRepo.getOne(dto.getVehicleId()));
        booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));
        for(equipmentDTO equipments:dto.getEquipments()){
            equipmentList.add(equipmentRepo.findById(equipments.getEquipmentId()).get());
        }
        booking.setEquipments(equipmentList);

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
        booking.setPrice(dto.getPrice());

        bookingRepo.save(booking);
    }


    public Booking updateBooking(bookingDTO dto) {
        Booking booking = bookingRepo.findById(dto.getBookingId()).get();
        booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
        booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));
        booking.setVehicle(dto.getVehicle());
//        booking.setEquipments(dto.getEquipments());
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

//                dto.setEquipments(bookings.getEquipments());
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
                dto.setBookingStatus(bookings.getBookingStatus());
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
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
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public List<bookingDTO> getAllCollectedBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Collected")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingStatus(bookings.getBookingStatus());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }


    public List<bookingDTO> getAllExtendRequestedBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Collected") && bookings.getIsLateReturn().equals("True")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    public List<bookingDTO> getAllCompletedBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            if(bookings.getBookingStatus().equals("Completed")){
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
                dto.setVehicle(bookings.getVehicle());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public String acceptBooking(acceptBookingDTO dto) {
        Optional<Booking> booking = bookingRepo.findById(dto.getBookingId());
         if(booking.isPresent()) {
             Booking book = booking.get();
             book.setBookingStatus("Accepted");
             bookingRepo.save(book);
             return "Booking Accepted";
         }
         return "Id Not Found";
    }

    public String rejectBooking(acceptBookingDTO dto) {
        Optional<Booking> booking = bookingRepo.findById(dto.getBookingId());
        if(booking.isPresent()) {
            Booking book = booking.get();
            book.setBookingStatus("Rejected");
            System.out.println(userRepo.findUserByEmail(dto.getEmail()));
            bookingRepo.save(book);
//            emailService.EmailForRejectBooking(dto.getEmail());
            return "Booking Rejected";
        }
        return "Id Not Found";
    }

    public void updateBookingStatus(acceptBookingDTO dto) throws Exception{
        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(
                ()->new Exception("Resource Not Found")
        );
        booking.setBookingStatus(dto.getStatus());
        bookingRepo.save(booking);
    }

    public void requestLateReturn(acceptBookingDTO dto) throws Exception{
        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(
                ()-> new Exception("Booking Id Not Found")
        );
        booking.setIsLateReturn("True");
        bookingRepo.save(booking);
    }




}
