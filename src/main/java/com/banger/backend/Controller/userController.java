package com.banger.backend.Controller;

import com.banger.backend.DTO.*;
import com.banger.backend.Entity.*;
import com.banger.backend.Exception.LicenseNumberExistException;
import com.banger.backend.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap;
import java.util.HashMap;
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
    public ResponseEntity<?> registerUser(@RequestParam("signUpInfo") String signUpInfo, @RequestParam("licenceImg") MultipartFile licence,
                                          @RequestParam("profileImage") MultipartFile profileImg, @RequestParam("utilityBill") MultipartFile utilityBill) throws IOException, ParseException, LicenseNumberExistException {
        ObjectMapper mp = new ObjectMapper();
        userDTO dtoUser = mp.readValue(signUpInfo, userDTO.class);
        dtoUser.setProfileImage(profileImg.getBytes());
        dtoUser.setLicenceImg(licence.getBytes());
        dtoUser.setUtilityBill(utilityBill.getBytes());
        userService.userRegistration(dtoUser);
        return new ResponseEntity(CREATED);
    }


    @PutMapping("/userUpdateProfile")
    public ResponseEntity<?> updateProfile(@RequestParam("updatedInfo") String updatedInfo,
                                           @RequestParam("utilityBill") MultipartFile utilityBill,
                                           @RequestParam("licenceImg") MultipartFile licenceImg) throws Exception {
        ObjectMapper mp = new ObjectMapper();
        userDTO dtoUser = mp.readValue(updatedInfo, userDTO.class);
        dtoUser.setUtilityBill(utilityBill.getBytes());
        dtoUser.setLicenceImg(licenceImg.getBytes());
        userService.updateUserProfile(dtoUser);
        return new ResponseEntity(CREATED);
    }


    @PostMapping("/makeBooking")
    public ResponseEntity<HashMap<String, String>> makeBooking(@RequestBody bookingDTO dto) throws Exception {
        this.bookingService.makeBooking(dto);
        HashMap<String, String> bookingInfo = new HashMap<>();
        bookingInfo.put("bookingInfo","success");
        return new ResponseEntity<HashMap<String, String>>(bookingInfo, OK);
    }

    @GetMapping("/getEquipmentList")
    public List<equipmentDTO> viewEquipmentList() {
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


    @PutMapping("requestLateReturn")
    public void requestLateReturn(@RequestBody bookingDTO dto) throws Exception {
        bookingService.requestLateReturn(dto);
    }

    @GetMapping("/getLoggedInUser/{email}")
    public User getLoggedInUser(@PathVariable(value = "email") String email) {
        return userService.getUserByID(email);
    }

    @GetMapping("/getEquipment/{equipmentId}")
    public Equipment getSelectedEquipment(@PathVariable(value = "equipmentId") int equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    @GetMapping("/viewAllVehicles/{email}")
    public List<vehicleDTO> viewAllVehicles(@PathVariable(value = "email") String email) {
        return vehicleService.getVehiclesAccordingToAge(email);
    }

    @GetMapping("/viewMyBookings/{email}")
    public List<bookingDTO> getMyBookings(@PathVariable(value = "email") String email) {
        return bookingService.getBookingsByUserEmail(email);
    }

    @GetMapping("/viewMyPBookings/{email}")
    public List<bookingDTO> getMyPendingBookings(@PathVariable(value = "email") String email) {
        return bookingService.getMyPendingBookings(email);
    }

    @GetMapping("/viewMyPreviousBookings/{email}")
    public List<bookingDTO> getMyPreviousBookings(@PathVariable(value = "email") String email) {
        return bookingService.getCompletedBookingsByUserEmail(email);
    }


    @PostMapping("/searchVehicles")
    public List<vehicleDTO> findAvailableVehicles(@RequestBody searchVehicleDTO searchDto) {
        return bookingService.searchAvailableVehiclesAccordingToThePickupTimeAndReturnTime(searchDto.getPickupTime(), searchDto.getReturnTime());
    }

    @DeleteMapping("cancelBooking/{bookingId}")
    public void cancelBooking(@PathVariable(name = "bookingId") int bookingId) {
        bookingService.removeBookings(bookingId);
    }

}
