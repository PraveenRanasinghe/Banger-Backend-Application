package com.banger.backend.Controller;

import com.banger.backend.DTO.*;
import com.banger.backend.Entity.*;
import com.banger.backend.Exception.EquipNameExistsException;
import com.banger.backend.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/admin")
public class adminController {

    @Autowired
    private userService userService;

    @Autowired
    private vehicleService vehicleService;

    @Autowired
    private equipmentService equipService;

    @Autowired
    private bookingService bookingService;

    @Autowired
    private inquiryService inqService;


    @PostMapping("/addVehicle")
    public ResponseEntity<?> addVehicle(@RequestParam("vehicleInfo") String vehicleInfo, @RequestParam("vehicleImage") MultipartFile file) throws IOException {
        ObjectMapper mp = new ObjectMapper();
        vehicleDTO dtoVehicle = mp.readValue(vehicleInfo, vehicleDTO.class);
        dtoVehicle.setVehicleImg(file.getBytes());
        vehicleService.addVehicle(dtoVehicle);
        return new ResponseEntity(CREATED);
    }


    @PostMapping("/addEquipment")
    public ResponseEntity<?> addEquipments(@RequestBody equipmentDTO dto) {
        try {
            Equipment addedEquip = equipService.addEquipments(dto);
            return new ResponseEntity(addedEquip, CREATED);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/viewInquiries")
    public List<Inquiry> viewAllInquiries() {
        return inqService.getAllInquiries();
    }

    @GetMapping("/getSingleInquiry/{inquiryId}")
    public Inquiry getInquiryById(@PathVariable(value = "inquiryId") int inquiryId) {
        return inqService.getInquiryById(inquiryId);
    }

    @DeleteMapping("removeInquiry/{inquiryId}")
    public void removeInquiry(@PathVariable(name = "inquiryId") int inquiryId) {
        inqService.deleteInquiry(inquiryId);
    }

    @DeleteMapping("removeEquipment/{equipId}")
    public void removeEquipment(@PathVariable(name = "equipId") int equipId) {
        equipService.removeEquipment(equipId);
    }

    @DeleteMapping("removeVehicle/{vehicleId}")
    public void removeVehicle(@PathVariable(name = "vehicleId") int vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

    @DeleteMapping("removeUser/{email}")
    public void removeUser(@PathVariable(name = "email") String email) {
        userService.removeUser(email);
    }

    @GetMapping("/viewAllVehicles")
    public List<Vehicle> viewAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("getSingleVehicle/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable(value = "vehicleId") int vehicleId) {
        return vehicleService.getVehicleByID(vehicleId);
    }

    @GetMapping("/viewPendingUsers")
    public List<userDTO> viewPendingUsers() {
        return userService.getAllPendingUsersToList();
    }


    @PutMapping("acceptUser/{email}")
    public User AcceptUser(@PathVariable(value = "email") String email, @RequestBody acceptUserDTO dtoUser) {
        return userService.acceptUserAccount(dtoUser);
    }

    @GetMapping("getBooking/{bookingId}")
    public Booking getBookingById(@PathVariable(value = "bookingId") int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping("/acceptBooking")
    public String AcceptBooking(@RequestBody acceptBookingDTO bookingDTO) {
        return bookingService.acceptBooking(bookingDTO);
    }


    @PostMapping("/rejectBooing")
    public String RejectBooking(@RequestBody acceptBookingDTO bookingDTO) {
        return bookingService.rejectBooking(bookingDTO);
    }


    @PutMapping("updateVehicle/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable(value = "vehicleId") int vehicleId, @RequestBody vehicleDTO vehicle) {
        return vehicleService.updateVehicleInfo(vehicle);
    }

    @PutMapping("updateEquipment/{equipmentId}")
    public Equipment updateEquipment(@PathVariable(value = "equipmentId") int equipmentId, @RequestBody equipmentDTO equipment) throws Exception {
        return equipService.updateEquipment(equipment);
    }


    @PutMapping("updateBookingStatus")
    public void updateBookingStatus(@RequestBody acceptBookingDTO dto) throws Exception {
        bookingService.updateBookingStatus(dto);
    }


    @GetMapping("/viewAllUsers")
    public List<userDTO> viewAllUsers() {
        return userService.getAllAcceptedUsersToList();
    }

    @GetMapping("/getSingleUser/{email}")
    public User getUserById(@PathVariable(value = "email") String userEmail) {
        return userService.getUserByID(userEmail);
    }

    @GetMapping("/viewEquipments")
    public List<equipmentDTO> viewAllEquipments() {
        return equipService.getEquipmentsToList();
    }

    @GetMapping("getSingleEquipment/{equipId}")
    public Equipment getEquipmentById(@PathVariable(name = "equipId") int equipId) {
        return equipService.getEquipmentById(equipId);
    }

    @GetMapping("/viewAllBookings")
    public List<bookingDTO> viewPendingBookings() {
        return bookingService.getAllPendingBookings();
    }

    @GetMapping("/viewAcceptedBookings")
    public List<bookingDTO> viewAcceptedBookings() {
        return bookingService.getAllAcceptedBookings();
    }


    @GetMapping("/viewCollectedBookings")
    public List<bookingDTO> viewCollectedBookings() {
        return bookingService.getAllCollectedBookings();
    }


    @GetMapping("/viewExtendRequestedBookings")
    public List<bookingDTO> viewExtendRequestedBookings(){return bookingService.getAllExtendRequestedBookings();}

    @GetMapping("/viewCompletedBookings")
    public List<bookingDTO> viewCompletedBookings() {
        return bookingService.getAllCompletedBookings();
    }


    @GetMapping("/viewRejectedBookings")
    public List<bookingDTO> viewRejectedBookings() {
        return bookingService.getAllRejectedBookings();
    }

    @GetMapping("/viewSingleBooking/{bookingId}")
    public Booking getSingleBooking(@PathVariable(name = "bookingId") int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/getLoggedInUser/{email}")
    public User getLoggedInUser(@PathVariable(value = "email") String email) {
        return userService.getUserByID(email);
    }

}
