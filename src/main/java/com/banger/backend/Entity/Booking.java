package com.banger.backend.Entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "pickup_date")
    private Date pickupDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "pickup_time")
    private LocalTime pickupTime;


    @Column(name = "return_time")
    private LocalTime returnTime;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "utility_bill")
    private byte[] utilityBill;

    @Column(name = "booking_status")
    private String bookingStatus;

    @ManyToMany
    @JoinTable(
            name = "booking_equipment",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )

    private List<Equipment> equipments;

    public Booking(int bookingId, Date pickupDate, Date returnDate, LocalTime pickupTime, LocalTime returnTime, User user, Vehicle vehicle, byte[] utilityBill, String bookingStatus, List<Equipment> equipments) {
        this.bookingId = bookingId;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.user = user;
        this.vehicle = vehicle;
        this.utilityBill = utilityBill;
        this.bookingStatus = bookingStatus;
        this.equipments = equipments;
    }

    public Booking(){

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

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalTime returnTime) {
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

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
