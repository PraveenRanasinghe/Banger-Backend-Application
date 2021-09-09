package com.banger.backend.TestService;
import com.banger.backend.DTO.equipmentDTO;
import com.banger.backend.Service.equipmentService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class EquipmentServiceTest {

    @Autowired
    private equipmentService equipmentService;

    @Test
    public void getAllEquipment(){
        List<equipmentDTO> allEquipment = equipmentService.getAvailableEquipmentsToList();
        assertThat(allEquipment,not(IsEmptyCollection.empty()));
    }
}
