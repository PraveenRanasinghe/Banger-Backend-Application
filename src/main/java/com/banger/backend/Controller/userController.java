package com.banger.backend.Controller;

import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.DTO.inquiryDTO;
import com.banger.backend.DTO.userDTO;
import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Service.bookingService;
import com.banger.backend.Service.inquiryService;
import com.banger.backend.Service.userService;
import com.banger.backend.Service.vehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/customer")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private bookingService bookingService;

    @Autowired
    private inquiryService inqService;

    @Autowired
    private vehicleService vehicleService;


    @PostMapping("/addInq")
    @PreAuthorize("permitAll()")
    public ResponseEntity<inquiryDTO> addInquiry(@RequestBody inquiryDTO dto) {
        Inquiry addInq = this.inqService.addInquiry(dto);
        return new ResponseEntity(addInq, OK);
    }

    @PostMapping("/createAccount")
    @PreAuthorize("permitAll()")
    public ResponseEntity<userDTO> registerUser(@RequestBody userDTO dto) throws ParseException {
        User addedUser = this.userService.userRegistration(dto);
        return new ResponseEntity(addedUser, CREATED);
    }

    @PostMapping("/userUpdateProfile")
    public ResponseEntity<userDTO> updateProfile(@RequestBody userDTO dto) {
        User updateUser = this.userService.updateUserProfile(dto);
        return new ResponseEntity(updateUser, OK);
    }

    @PostMapping("/makeBooking")
    public ResponseEntity<bookingDTO> makeBooking(@RequestBody bookingDTO dto) {
        Booking makeBooking = this.bookingService.makeBooking(dto);
        return new ResponseEntity(makeBooking, OK);
    }

    @PostMapping("/updateBooking")
    public ResponseEntity<bookingDTO> updateBooking(@RequestBody bookingDTO dto) {
        Booking updateBooking = this.bookingService.updateBooking(dto);
        return new ResponseEntity(updateBooking, OK);
    }

    @GetMapping("/viewAllVehicles")
    public List<Vehicle> viewAllVehicles(){
        return vehicleService.getAllVehicles();
    }

}
