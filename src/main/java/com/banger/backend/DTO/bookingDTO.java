package com.banger.backend.DTO;

import com.banger.backend.Entity.Equipment;
import com.banger.backend.Entity.User;
import com.banger.backend.Entity.Vehicle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class bookingDTO {

    private int bookingId;
    private String pickupTime;
    private String returnTime;
    private String isLateReturn;
    private User user;
    private Vehicle vehicle;
    private int vehicleId;
    private String email;
    private List<Equipment> equipments;
    private String bookingStatus;

    public bookingDTO() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getIsLateReturn() {
        return isLateReturn;
    }

    public void setIsLateReturn(String isLateReturn) {
        this.isLateReturn = isLateReturn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
