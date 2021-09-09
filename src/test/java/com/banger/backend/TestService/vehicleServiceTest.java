package com.banger.backend.TestService;

import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Service.vehicleService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class vehicleServiceTest {

    @Autowired
    private vehicleService vehicleService;

    @Test
    public void getAllVehicles(){
        List<Vehicle> vehicles =vehicleService.getAllVehicles();
        assertThat(vehicles,not(IsEmptyCollection.empty()));
    }

}
