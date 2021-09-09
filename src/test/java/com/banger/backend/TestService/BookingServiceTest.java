package com.banger.backend.TestService;

import com.banger.backend.DTO.bookingDTO;
import com.banger.backend.Service.bookingService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private bookingService bookingService;

    @Test
    public void getAllPendingBookings(){
        List<bookingDTO> bookingList = bookingService.getAllPendingBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllBookings(){
        List<bookingDTO> bookingList = bookingService.getAllBookingsToList();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllAcceptedBookings(){
        List<bookingDTO> bookingList = bookingService.getAllAcceptedBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllRejectedBookings(){
        List<bookingDTO> bookingList = bookingService.getAllRejectedBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllCompletedBookings(){
        List<bookingDTO> bookingList = bookingService.getAllCompletedBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllCollectedBookings(){
        List<bookingDTO> bookingList = bookingService.getAllCollectedBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getExtendedBookings(){
        List<bookingDTO> bookingList = bookingService.getAllExtendRequestedBookings();
        assertThat(bookingList,not(IsEmptyCollection.empty()));
    }

}
