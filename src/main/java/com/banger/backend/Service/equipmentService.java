package com.banger.backend.Service;

import com.banger.backend.DTO.equipmentDTO;
import com.banger.backend.Entity.Equipment;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Exception.EquipNameExistsException;
import com.banger.backend.Repositary.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class equipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    public Equipment getEquipmentById(Integer equipId) {
        Optional<Equipment> equipment = equipmentRepo.findById(equipId);
        Equipment equip = null;
        if (equipment.isPresent()) {
            equip = equipment.get();
        }
        return equip;
    }


    public List<equipmentDTO> getEquipmentsToList() {
        List<equipmentDTO> list = new ArrayList<>();
        for (Equipment equipment : equipmentRepo.findAll()) {
            equipmentDTO dtoEq = new equipmentDTO();
            dtoEq.setEquipmentId(equipment.getEquipmentId());
            dtoEq.setItemName(equipment.getItemName());
            dtoEq.setPricePerDayEQ(equipment.getPricePerDayEQ());
            list.add(dtoEq);
        }
        return list;
    }


    public List<Equipment> getAllEquipments() {
        return equipmentRepo.findAll();
    }

    public Equipment addEquipments(equipmentDTO dto) throws EquipNameExistsException {

        Equipment equipment = new Equipment();
        if(equipmentRepo.findByItemName(dto.getItemName()).isPresent()){
            throw new EquipNameExistsException("Equipment Name is Already Exists! Please Update the Quantity!");
        }
        else{
            equipment.setQuantity(dto.getQuantity());
            equipment.setItemName(dto.getItemName());
            equipment.setItemDescription(dto.getItemDescription());
            equipment.setPricePerDayEQ(dto.getPricePerDayEQ());
        }

        return equipmentRepo.save(equipment);
    }

    public Equipment updateEquipment(equipmentDTO dto) throws Exception {
        Equipment equipment = equipmentRepo.findById(dto.getEquipmentId()).orElseThrow(
                () -> new Exception("Resource Not Found")
        );
        equipment.setItemDescription(dto.getItemDescription());
        equipment.setPricePerDayEQ(dto.getPricePerDayEQ());
        equipment.setQuantity(dto.getQuantity());
        return equipmentRepo.save(equipment);
    }

    public void removeEquipment(int equipment) {
        equipmentRepo.deleteById(equipment);
    }


}
