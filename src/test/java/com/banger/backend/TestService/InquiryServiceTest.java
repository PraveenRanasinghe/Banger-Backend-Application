package com.banger.backend.TestService;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Service.inquiryService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class InquiryServiceTest {

    @Autowired
    private inquiryService inquiryService;

    @Test
    public void getAllInquiries(){
        List<Inquiry> allBatches = inquiryService.getAllInquiries();
        assertThat(allBatches,not(IsEmptyCollection.empty()));
    }

}
