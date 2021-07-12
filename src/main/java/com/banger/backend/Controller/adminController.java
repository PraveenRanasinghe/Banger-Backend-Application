package com.banger.backend.Controller;

import com.banger.backend.DTO.equipmentDTO;
import com.banger.backend.DTO.userDTO;
import com.banger.backend.DTO.vehicleDTO;
import com.banger.backend.Entity.*;
import com.banger.backend.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    @PostMapping("/addVehicle")
//    public ResponseEntity<vehicleDTO> addVehicle(@RequestBody vehicleDTO dto){
//        Vehicle addedVehicle= this.vehicleService.addVehicle(dto);
//        return new ResponseEntity(addedVehicle, CREATED);
//    }

    @PostMapping("/addVehicle")
    public ResponseEntity<?>  uploadImage(@RequestParam("vehicleInfo")String vehicleInfo, @RequestParam("vehicleImage")MultipartFile file) throws IOException {
        ObjectMapper mp = new ObjectMapper();
        vehicleDTO dtoVehicle = mp.readValue(vehicleInfo, vehicleDTO.class);
        dtoVehicle.setVehicleImg(file.getBytes());
        vehicleService.addVehicle(dtoVehicle);
        return new ResponseEntity(CREATED);
    }


    @PostMapping("/addEquipment")
    public ResponseEntity<equipmentDTO> addEquipments(@RequestBody equipmentDTO dto){
       Equipment addedEquip= this.equipService.addEquipments(dto);
        return new ResponseEntity(addedEquip, CREATED);
    }

    @GetMapping("/viewInquiries")
    public List<Inquiry> viewAllInquiries(){
        return inqService.getAllInquiries();
    }

    @GetMapping("/getSingleInquiry/{inquiryId}")
    public Inquiry getInquiryById(@PathVariable(value = "inquiryId") int inquiryId ){
        return inqService.getInquiryById(inquiryId);
    }

    @RequestMapping("removeInquiry/{inquiryId}")
    public void removeInquiry(@PathVariable(name = "inquiryId") Inquiry inquiry){
        inqService.deleteInquiry(inquiry);
    }

    @GetMapping("/viewAllVehicles")
    public List<Vehicle> viewAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("getSingleVehicle/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable(value = "vehicleId") int vehicleId){
        return vehicleService.getVehicleByID(vehicleId);
    }

    @GetMapping("/viewPendingUsers")
    public List<userDTO> viewPendingUsers(){
        return userService.getAllPendingUsersToList();
    }

    @GetMapping("/viewAllUsers")
    public List<userDTO> viewAllUsers(){
        return userService.getAllUsersToList();
    }

    @GetMapping("/getSingleUser/{email}")
    public User getUserById(@PathVariable(value = "email") String userEmail){
        return userService.getUserByID(userEmail);
    }

    @GetMapping("/viewEquipments")
    public List<Equipment>viewAllEquipments(){
        return equipService.getAllEquipments();
    }

    @GetMapping("getSingleEquipment/{equipId}")
    public Equipment getEquipmentById(@PathVariable(name = "equipId") int equipId){
        return equipService.getEquipmentById(equipId);
    }

    @GetMapping("/viewAllBookings")
    public List<Booking> viewAllBookings(){
        return bookingService.getAllBookingsToList();
    }

}
