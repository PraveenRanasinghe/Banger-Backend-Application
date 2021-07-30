package com.banger.backend.Service;

import com.banger.backend.DTO.vehicleDTO;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Repositary.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class vehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;


    public Vehicle getVehicleByID(Integer vId) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(vId);
        Vehicle v = null;
        if (vehicle.isPresent()) {
            v = vehicle.get();
        }
        return v;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepo.findAll();
    }

    public Vehicle addVehicle(vehicleDTO dtoVehicle){
        Vehicle vehicle= new Vehicle();
        vehicle.setVehicleId(dtoVehicle.getVehicleId());
        vehicle.setVehicleModel(dtoVehicle.getVehicleModel());
        vehicle.setVehicleType(dtoVehicle.getVehicleType());
        vehicle.setAc(dtoVehicle.getAc());
        vehicle.setAirBag(dtoVehicle.getAirBag());
        vehicle.setFuelType(dtoVehicle.getFuelType());
        vehicle.setNumOfSeats(dtoVehicle.getNumOfSeats());
        vehicle.setPricePerDay(dtoVehicle.getPricePerDay());
        vehicle.setTransmissionType(dtoVehicle.getTransmissionType());
        vehicle.setVehicleImg(dtoVehicle.getVehicleImg());
        return vehicleRepo.save(vehicle);
    }

    public Vehicle updateVehicleInfo(vehicleDTO dtoVehicle){

        Vehicle vehicle = vehicleRepo.findById(dtoVehicle.getVehicleId()).get();

        vehicle.setFuelType(dtoVehicle.getFuelType());
        vehicle.setPricePerDay(dtoVehicle.getPricePerDay());
        vehicle.setTransmissionType(dtoVehicle.getTransmissionType());
        vehicle.setAirBag(dtoVehicle.getAirBag());
        vehicle.setAc(dtoVehicle.getAc());

        return vehicleRepo.save(vehicle);
    }

    public void deleteVehicle(int vehicleId){
        vehicleRepo.deleteById(vehicleId);
    }

    
}
