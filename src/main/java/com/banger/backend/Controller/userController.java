package com.banger.backend.Controller;

import com.banger.backend.DTO.*;
import com.banger.backend.Entity.*;
import com.banger.backend.Service.*;
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

    @Autowired
    private equipmentService equipmentService;


    @PostMapping("/addInq")
    @PreAuthorize("permitAll()")
    public ResponseEntity<inquiryDTO> addInquiry(@RequestBody inquiryDTO dto) {
        Inquiry addInq = this.inqService.addInquiry(dto);
        return new ResponseEntity(addInq, OK);
    }


    @PostMapping("/createAccount")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerUser(@RequestParam("signUpInfo") String signUpInfo, @RequestParam("licenceImg")MultipartFile licence,
                                          @RequestParam("profileImage") MultipartFile profileImg, @RequestParam("utilityBill")MultipartFile utilityBill) throws IOException, ParseException {
        ObjectMapper mp = new ObjectMapper();
        userDTO dtoUser= mp.readValue(signUpInfo,userDTO.class);
        dtoUser.setProfileImage(profileImg.getBytes());
        dtoUser.setLicenceImg(licence.getBytes());
        dtoUser.setUtilityBill(utilityBill.getBytes());
        userService.userRegistration(dtoUser);
        return new ResponseEntity(CREATED);
    }



    @PostMapping("/userUpdateProfile")
    public ResponseEntity<userDTO> updateProfile(@RequestBody userDTO dto) {
        User updateUser = this.userService.updateUserProfile(dto);
        return new ResponseEntity(updateUser, OK);
    }

    @PostMapping("makeBooking/")
    public ResponseEntity<bookingDTO> makeBooking(@RequestBody bookingDTO dto) {
        Booking makeBooking = this.bookingService.makeBooking(dto);
        return new ResponseEntity(makeBooking,OK);
    }

    @GetMapping("/getEquipmentList")
    public List<equipmentDTO> viewEquipmentList(){
        return equipmentService.getEquipmentsToList();
    }

    @GetMapping("getSingleVehicle/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable(value = "vehicleId") int vehicleId) {
        return vehicleService.getVehicleByID(vehicleId);
    }


    @PostMapping("/updateBooking")
    public ResponseEntity<bookingDTO> updateBooking(@RequestBody bookingDTO dto) {
        Booking updateBooking = this.bookingService.updateBooking(dto);
        return new ResponseEntity(updateBooking, OK);
    }

    @GetMapping("/getLoggedInUser/{email}")
    public User getLoggedInUser(@PathVariable(value = "email")String email){
        return userService.getUserByID(email);
    }

    @GetMapping("/viewAllVehicles")
    public List<Vehicle> viewAllVehicles(){
        return vehicleService.getAllVehicles();
    }

}
