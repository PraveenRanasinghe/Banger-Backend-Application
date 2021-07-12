package com.banger.backend.Controller;

import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.DTO.inquiryDTO;
import com.banger.backend.DTO.userDTO;
import com.banger.backend.DTO.vehicleDTO;
import com.banger.backend.Entity.Booking;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Service.bookingService;
import com.banger.backend.Service.inquiryService;
import com.banger.backend.Service.userService;
import com.banger.backend.Service.vehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @PostMapping("/addVehicle")
//    public ResponseEntity<?>  uploadImage(@RequestParam("vehicleInfo")String vehicleInfo, @RequestParam("vehicleImage") MultipartFile file) throws IOException {
//        ObjectMapper mp = new ObjectMapper();
//        vehicleDTO dtoVehicle = mp.readValue(vehicleInfo, vehicleDTO.class);
//        dtoVehicle.setVehicleImg(file.getBytes());
//        vehicleService.addVehicle(dtoVehicle);
//        return new ResponseEntity(CREATED);
//    }

    @PostMapping("/createAccount")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerUser(@RequestParam("signUpInfo") String signUpInfo, @RequestParam("licenceImg")MultipartFile licence,@RequestParam("profileImage") MultipartFile profileImg) throws IOException, ParseException {
        ObjectMapper mp = new ObjectMapper();
        userDTO dtoUser= mp.readValue(signUpInfo,userDTO.class);
        dtoUser.setProfileImage(profileImg.getBytes());
        dtoUser.setLicenceImg(licence.getBytes());
        userService.userRegistration(dtoUser);
        return new ResponseEntity(CREATED);
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
