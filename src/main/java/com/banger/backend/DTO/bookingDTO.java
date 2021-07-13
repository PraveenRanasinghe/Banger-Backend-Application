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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date pickupDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date returnDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private String pickupTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private String returnTime;

    private User user;
    private Vehicle vehicle;
    private List<Equipment> equipments;
    private byte[] utilityBill;
    private String bookingStatus;

    public bookingDTO() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

    public byte[] getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(byte[] utilityBill) {
        this.utilityBill = utilityBill;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
