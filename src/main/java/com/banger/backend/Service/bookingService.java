package com.banger.backend.Service;

import com.banger.backend.DTO.*;
import com.banger.backend.Entity.*;
import com.banger.backend.Repositary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private InsuranceRepo insuranceRepo;

    @Autowired
    private emailService emailService;

    @Autowired
    private vehicleService vehicleService;

    public Booking getBookingById(Integer bookingId) {
        Optional<Booking> bookings = bookingRepo.findById(bookingId);
        Booking booking = null;
        if (bookings.isPresent()) {
            booking = bookings.get();
        }
        return booking;
    }


    public List<bookingDTO> getAllBookingsToList() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();
        List<equipmentDTO> dtoListEquip = new ArrayList<>();


        for (Booking bookings : bookingList) {
            bookingDTO dto = new bookingDTO();
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

    public List<bookingDTO> getBookingsByUserEmail(String email) {
        List<Booking> bookingList = bookingRepo.findBookingsByUserEmail(email);
        List<bookingDTO> dtoList = new ArrayList<>();

        for (Booking bookings : bookingList) {
            if (bookings.getBookingStatus().equals("Collected")) {
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


    public List<bookingDTO> getMyPendingBookings(String email) {
        List<Booking> bookingList = bookingRepo.findBookingsByUserEmail(email);
        List<bookingDTO> dtoList = new ArrayList<>();

        for (Booking bookings : bookingList) {
            if (bookings.getBookingStatus().equals("Pending")) {
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

    public List<bookingDTO> getCompletedBookingsByUserEmail(String email) {
        List<Booking> bookingList = bookingRepo.findBookingsByUserEmail(email);
        List<bookingDTO> dtoList = new ArrayList<>();

        for (Booking bookings : bookingList) {
            if (bookings.getBookingStatus().equals("Completed")) {
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


    @Transactional
    public void makeBooking(bookingDTO dto) throws Exception {

        User user1 = userRepo.findUserByEmail(dto.getEmail());

        List<Insurance> insurances = insuranceRepo.findAll();
        for (int j=0; j<insurances.size(); j++){
            if (Objects.equals(user1.getNicNumber(), insurances.get(j).getLicenseNumber())) {
                throw new Exception("Caught for Fraudulent Claims!");
            }
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\My Projects\\EIRLS\\Backend\\EIRLS-Backend\\src\\main\\java\\com\\banger\\backend\\FileWriter\\License.csv"));
        String licenseNumber = "";

        List<String> stringList = new ArrayList<>();
        while ((licenseNumber = bufferedReader.readLine()) != null) {
            stringList.add(licenseNumber);
        }

        for (int i = 0; i < stringList.size(); i++) {
            if (Objects.equals(stringList.get(i), user1.getNicNumber())) {
                user1.setIsBlackListed("True");
                emailService.sendEmailToDMV("praveenranasinghe43@gmail.com", user1.getProfileImage(), user1.getNicNumber());
                throw new Exception("Your booking cannot be completed !");
            }
        }

        Booking booking = new Booking();
        List<Equipment> equipmentList = new ArrayList<>();
        User user = userRepo.findUserByEmail(dto.getEmail());

        if (user.getIsBlackListed().equals("False") && user.getUserRole().equals("Customer")) {
            booking.setVehicle(vehicleRepo.getOne(dto.getVehicleId()));
            booking.setPickupTime(LocalDateTime.parse(dto.getPickupTime()));
            booking.setReturnTime(LocalDateTime.parse(dto.getReturnTime()));
            for (equipmentDTO equipments : dto.getEquipments()) {
                equipmentList.add(equipmentRepo.findById(equipments.getEquipmentId()).get());
            }
            booking.setEquipments(equipmentList);

            List<Booking> bookingList = bookingRepo.findBookingByPickupTimeAndReturnTime(LocalDateTime.parse(dto.getPickupTime()),
                    LocalDateTime.parse(dto.getReturnTime()));

            for (Booking bookingInfo : bookingList) {
                if ((LocalDateTime.parse((dto.getPickupTime())).isAfter(bookingInfo.getPickupTime()))
                        && (LocalDateTime.parse((dto.getPickupTime())).isBefore(bookingInfo.getReturnTime()))) {
                    throw new Exception("You cannot Make the Booking at this moment.Because this vehicle is Already booked for selected Time Period!");
                } else if ((LocalDateTime.parse((dto.getPickupTime())).isAfter(bookingInfo.getPickupTime()))
                        && (LocalDateTime.parse((dto.getReturnTime())).isBefore(bookingInfo.getReturnTime()))) {
                    throw new Exception("You cannot Make the Booking at this moment.Because this vehicle is Already booked for selected Time Period!");
                }
            }
            booking.setUser(userRepo.getOne(dto.getEmail()));
            booking.setBookingStatus("Pending");
            booking.setIsLateReturn("False");
            booking.setPrice(dto.getPrice());
            bookingRepo.save(booking);
        } else {
            throw new Exception("Your Account Has Been BlackListed! You will not be able to make booking again in Banger & Co Organization.!");
        }

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

    public void removeBookings(Booking booking) {
        bookingRepo.delete(booking);
    }


    public List<bookingDTO> getAllPendingBookings() {
        List<Booking> bookingList = bookingRepo.findAll();
        List<bookingDTO> dtoList = new ArrayList<>();

        for (Booking bookings : bookingList) {
            if (bookings.getBookingStatus().equals("Pending")) {
                bookingDTO dto = new bookingDTO();
                dto.setEmail(bookings.getUser().getEmail());
                dto.setBookingId(bookings.getBookingId());
                dto.setReturnTime(bookings.getReturnTime().toString());
                dto.setPickupTime(bookings.getPickupTime().toString());
                dto.setPrice(bookings.getPrice());
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
            if (bookings.getBookingStatus().equals("Accepted")) {
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
            if (bookings.getBookingStatus().equals("Rejected")) {
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
            if (bookings.getBookingStatus().equals("Collected")) {
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
            if (bookings.getBookingStatus().equals("Collected") && bookings.getIsLateReturn().equals("True")) {
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
        List<Booking> bookingList = bookingRepo.findByBookingStatus("Completed");
        List<bookingDTO> dtoList = new ArrayList<>();
        for (Booking bookings : bookingList) {
            bookingDTO dto = new bookingDTO();
            dto.setEmail(bookings.getUser().getEmail());
            dto.setBookingId(bookings.getBookingId());
            dto.setReturnTime(bookings.getReturnTime().toString());
            dto.setPickupTime(bookings.getPickupTime().toString());
//                dto.setEquipments(bookings.getEquipments());
            dto.setVehicle(bookings.getVehicle());

            dtoList.add(dto);
        }
        return dtoList;
    }

    public String acceptBooking(acceptBookingDTO dto) {
        Optional<Booking> booking = bookingRepo.findById(dto.getBookingId());
        if (booking.isPresent()) {
            Booking book = booking.get();
            book.setBookingStatus("Accepted");
            emailService.EmailToNotifyUserInBookingAcceptance(dto.getEmail());
            bookingRepo.save(book);
            return "Booking Accepted";
        }
        return "Id Not Found";
    }

    public String rejectBooking(acceptBookingDTO dto) {
        Optional<Booking> booking = bookingRepo.findById(dto.getBookingId());
        if (booking.isPresent()) {
            Booking book = booking.get();
            book.setBookingStatus("Rejected");
            System.out.println(userRepo.findUserByEmail(dto.getEmail()));
            bookingRepo.save(book);
            emailService.EmailForRejectBooking(dto.getEmail());
            return "Booking Rejected";
        }
        return "Id Not Found";
    }


    public void blackListUserWhenBookingStateChange(acceptBookingDTO dto) throws Exception {
        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(
                () -> new Exception("Resource Not Found")
        );
        booking.setBookingStatus(dto.getStatus());

        if (dto.getStatus().equals("Not-Collected")) {
            User user = userRepo.findUserByEmail(dto.getEmail());
            emailService.emailForBlackListUsers(dto.getEmail());
            user.setIsBlackListed("True");
        }
        bookingRepo.save(booking);
    }


    public void requestLateReturn(bookingDTO dto) throws Exception {

        Booking booking = bookingRepo.findById(dto.getBookingId()).orElseThrow(
                () -> new Exception("Booking Id Not Found")
        );
//
//        List<Booking> bookingList = bookingRepo.findByBookingStatus("Accepted");
//
//        for(Booking bookings:bookingList){
//            bookings.
//        }
//

        if (bookingRepo.findById(dto.getBookingId()).isPresent()) {
            booking.setIsLateReturn("True");
            bookingRepo.save(booking);
        } else throw new Exception("Booking Extend Request Cannot be Accepted!.");

    }


    public List<vehicleDTO> searchAvailableVehiclesAccordingToThePickupTimeAndReturnTime(String pickupTime, String returnTime) {

        List<vehicleDTO> vehicleDTOS = new ArrayList<>();
        List<Booking> bookingList = bookingRepo.findByBookingStatus("Accepted");
        bookingList.addAll(bookingRepo.findByBookingStatus("Collected"));
        List<Booking> filteredList = new ArrayList<>();
        //filtered list contains all the bookings which are between the pick up time and return time

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        List<Vehicle> finalList = vehicleService.getAllVehicles();

        List<Vehicle> notAvailableVehicles = new ArrayList<>();
        //contains all the vehicles which are not available between pickup time and return time

        for (Booking bookingInfo : bookingList) {
            if ((LocalDateTime.parse(pickupTime)).isAfter(bookingInfo.getPickupTime())
                    && (LocalDateTime.parse(pickupTime).isBefore(bookingInfo.getReturnTime()))) {
                filteredList.add(bookingInfo);
            } else if ((LocalDateTime.parse(returnTime).isAfter(bookingInfo.getPickupTime()))
                    && (LocalDateTime.parse(returnTime)).isBefore(bookingInfo.getReturnTime())) {
                filteredList.add(bookingInfo);
            }
        }

        for (Booking booking : filteredList) {
            notAvailableVehicles.add(booking.getVehicle());
        }

        for (Vehicle vehicle : vehicleList) {
            for (Vehicle notAvailableVehicle : notAvailableVehicles) {
                if (vehicle.equals(notAvailableVehicle)) {
                    finalList.remove(vehicle);
                }
            }
        }

        for (Vehicle vehicle : finalList) {
            vehicleDTO dto = new vehicleDTO();
            dto.setVehicleId(vehicle.getVehicleId());
            dto.setAc(vehicle.getAc());
            dto.setAirBag(vehicle.getAirBag());
            dto.setFuelType(vehicle.getFuelType());
            dto.setNumOfSeats(vehicle.getNumOfSeats());
            dto.setPricePerDay(vehicle.getPricePerDay());
            dto.setTransmissionType(vehicle.getTransmissionType());
            dto.setVehicleImg(vehicle.getVehicleImg());
            dto.setVehicleModel(vehicle.getVehicleModel());
            dto.setVehicleType(vehicle.getVehicleType());

            vehicleDTOS.add(dto);
        }

        return vehicleDTOS;
    }

}
