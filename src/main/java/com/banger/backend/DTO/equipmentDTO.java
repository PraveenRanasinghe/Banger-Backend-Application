package com.banger.backend.DTO;

import com.banger.backend.Entity.Booking;
import java.sql.Blob;
import java.util.List;

public class equipmentDTO {

    private int equipmentId;
    private String itemName;
    private String itemDescription;
    private String pricePerDayEQ;
    private int quantity;
    private Byte[] equipImg;
    private List<Booking> bookings;


    public equipmentDTO() {
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
