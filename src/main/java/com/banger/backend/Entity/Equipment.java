package com.banger.backend.Entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @Column(name = "equipment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int equipmentId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "price_per_day_eq")
    private String pricePerDayEQ;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "equip_img")
    private Byte[] equipImg;

    @ManyToMany
    @JoinTable(
            name = "booking_equipment",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id")
    )

    private List<Booking> bookings;

    public Equipment(){

    }

    public Equipment(int equipmentId, String itemName, String itemDescription, String pricePerDayEQ, int quantity, Byte[] equipImg, List<Booking> bookings) {
        this.equipmentId = equipmentId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.pricePerDayEQ = pricePerDayEQ;
        this.quantity = quantity;
        this.equipImg = equipImg;
        this.bookings = bookings;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPricePerDayEQ() {
        return pricePerDayEQ;
    }

    public void setPricePerDayEQ(String pricePerDayEQ) {
        this.pricePerDayEQ = pricePerDayEQ;
    }

    public Byte[] getEquipImg() {
        return equipImg;
    }

    public void setEquipImg(Byte[] equipImg) {
        this.equipImg = equipImg;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
