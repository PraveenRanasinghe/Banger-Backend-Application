package com.banger.backend.Service;

import com.banger.backend.DTO.equipmentDTO;
import com.banger.backend.Entity.Equipment;
import com.banger.backend.Entity.Inquiry;
import com.banger.backend.Repositary.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class equipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    public Equipment getEquipmentById(Integer equipId){
        Optional<Equipment> equipment = equipmentRepo.findById(equipId);
        Equipment equip = null;
        if (equipment.isPresent()) {
            equip = equipment.get();
        }
        return equip;
    }

    public List<Equipment> getAllEquipments(){
        return equipmentRepo.findAll();
    }

    public Equipment addEquipments(equipmentDTO dto){

        Equipment equipment= new Equipment();

        equipment.setEquipmentId(dto.getEquipmentId());
//        equipment.setEquipImg(dto.getEquipImg());
        equipment.setItemName(dto.getItemName());
        equipment.setItemDescription(dto.getItemDescription());
        equipment.setPricePerDayEQ(dto.getPricePerDayEQ());
        return equipmentRepo.save(equipment);
    }

    public Equipment updateEquipment(equipmentDTO dto){
        Equipment equipment= new Equipment();

        equipment.setEquipmentId(dto.getEquipmentId());
        equipment.setEquipImg(dto.getEquipImg());
        equipment.setItemName(dto.getItemName());
        equipment.setItemDescription(dto.getItemDescription());
        equipment.setPricePerDayEQ(dto.getPricePerDayEQ());
        return equipmentRepo.save(equipment);
    }

    public void removeEquipment(Equipment equipment){
        equipmentRepo.delete(equipment);
    }


}
