package com.banger.backend.Service;

import com.banger.backend.DTO.userDTO;
import com.banger.backend.DTO.vehicleDTO;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import com.banger.backend.Repositary.UserRepo;
import com.banger.backend.Repositary.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class vehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private UserRepo userRepo;


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


    public List<vehicleDTO> getVehiclesAccordingToAge(String email){

        User user = userRepo.findUserByEmail(email);
        LocalDate date = user.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate present = LocalDate.now();
        Period difference = Period.between(date, present);

        if(difference.getYears() < 25){
            List<vehicleDTO> vehicleDTOS = new ArrayList<>();
            for(Vehicle vehicle:vehicleRepo.findAll()){
                if(vehicle.getVehicleModel().equals("Small-Town Car")){
                    vehicleDTO dto = new vehicleDTO();
                    dto.setVehicleImg(vehicle.getVehicleImg());
                    dto.setAc(vehicle.getAc());
                    dto.setVehicleType(vehicle.getVehicleType());
                    dto.setVehicleModel(vehicle.getVehicleModel());
                    dto.setAirBag(vehicle.getAirBag());
                    dto.setFuelType(vehicle.getFuelType());
                    dto.setFuelType(vehicle.getFuelType());
                    dto.setNumOfSeats(vehicle.getNumOfSeats());
                    dto.setPricePerDay(vehicle.getPricePerDay());
                    dto.setTransmissionType(vehicle.getTransmissionType());

                    vehicleDTOS.add(dto);
                }
            }
            return vehicleDTOS;
        }
        else {
            List<vehicleDTO> vehicleDTOS = new ArrayList<>();
            for(Vehicle vehicle:vehicleRepo.findAll()){
                    vehicleDTO dto = new vehicleDTO();
                    dto.setVehicleImg(vehicle.getVehicleImg());
                    dto.setAc(vehicle.getAc());
                    dto.setVehicleType(vehicle.getVehicleType());
                    dto.setVehicleModel(vehicle.getVehicleModel());
                    dto.setAirBag(vehicle.getAirBag());
                    dto.setFuelType(vehicle.getFuelType());
                    dto.setFuelType(vehicle.getFuelType());
                    dto.setNumOfSeats(vehicle.getNumOfSeats());
                    dto.setPricePerDay(vehicle.getPricePerDay());
                    dto.setTransmissionType(vehicle.getTransmissionType());
                    vehicleDTOS.add(dto);
                }

            return vehicleDTOS;
        }
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
