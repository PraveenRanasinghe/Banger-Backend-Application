package com.banger.backend.Repositary;

import com.banger.backend.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment,Integer> {
}
