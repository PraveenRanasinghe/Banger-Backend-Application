package com.banger.backend.Controller;

import com.banger.backend.DTO.inquiryDTO;
import com.banger.backend.DTO.vehicleDTO;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Service.inquiryService;
import com.banger.backend.Service.vehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class guestController {
    @Autowired
    private inquiryService inqService;

    @Autowired
    private vehicleService vehicleService;

    @PostMapping("/guestAddInq")
    @PreAuthorize("permitAll()")
    public ResponseEntity<inquiryDTO> guestAddInquiry(@RequestBody inquiryDTO dto){
        Inquiry addInq= this.inqService.addInquiry(dto);
        return new ResponseEntity(addInq,OK);
    }

    @GetMapping("/guestViewVehicles")
    public List<Vehicle> viewAllVehicles() {
        return vehicleService.getAllVehicles();
    }

}
