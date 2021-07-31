package com.banger.backend.Repositary;

import com.banger.backend.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepo extends JpaRepository<Equipment,Integer> {
    Optional<Equipment> findByItemName(String itemName);
}
