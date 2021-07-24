package com.banger.backend.Service;

import com.banger.backend.DTO.inquiryDTO;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Repositary.InquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class inquiryService {

    @Autowired
    private InquiryRepo inquiryRepo;

    public List<Inquiry> getAllInquiries(){
        return inquiryRepo.findAll();
    }

    public Inquiry addInquiry(inquiryDTO dtoInq){

        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(dtoInq.getInquiryId());
        inquiry.setInquirerName(dtoInq.getInquirerName());
        inquiry.setInquirerEmail(dtoInq.getInquirerEmail());
        inquiry.setContactNum(dtoInq.getContactNum());
        inquiry.setMessage(dtoInq.getMessage());
        return inquiryRepo.save(inquiry);
    }


    public Inquiry getInquiryById(Integer inqId){
        Optional <Inquiry> inquiry= inquiryRepo.findById(inqId);
        Inquiry inq = null;
         if(inquiry.isPresent()){
            inq =inquiry.get();
         }
         return inq;
    }


    public void deleteInquiry(int inquiry){
        inquiryRepo.deleteById(inquiry);
    }


}
