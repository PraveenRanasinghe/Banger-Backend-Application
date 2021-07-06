package com.banger.backend.Repositary;

import com.banger.backend.Entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepo extends JpaRepository<Inquiry, Integer> {
}
