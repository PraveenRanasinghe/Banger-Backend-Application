package com.banger.backend.Controller;

import com.banger.backend.DTO.inquiryDTO;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Service.inquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class guestController {
    @Autowired
    private inquiryService inqService;

    @PostMapping("/guestAddInq")
    @PreAuthorize("permitAll()")
    public ResponseEntity<inquiryDTO> guestAddInquiry(@RequestBody inquiryDTO dto){
        Inquiry addInq= this.inqService.addInquiry(dto);
        return new ResponseEntity(addInq,OK);
    }

}
