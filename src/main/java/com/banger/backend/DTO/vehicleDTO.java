package com.banger.backend.DTO;


import javax.persistence.Column;

public class vehicleDTO {
    private int vehicleId;
    private String vehicleType;
    private String vehicleModel;
    private Integer numOfSeats;
    private String fuelType;
    private String pricePerDay;
    private String transmissionType;
    private String airBag;
    private String ac;
    private Byte[] vehicleImg;

    public vehicleDTO(int vehicleId, String vehicleType, String vehicleModel, Integer numOfSeats, String fuelType, String pricePerDay, String transmissionType, String airBag, String ac, Byte[] vehicleImg) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.numOfSeats = numOfSeats;
        this.fuelType = fuelType;
        this.pricePerDay = pricePerDay;
        this.transmissionType = transmissionType;
        this.airBag = airBag;
        this.ac = ac;
        this.vehicleImg = vehicleImg;
    }

    public vehicleDTO() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getAirBag() {
        return airBag;
    }

    public void setAirBag(String airBag) {
        this.airBag = airBag;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public Byte[] getVehicleImg() {
        return vehicleImg;
    }

    public void setVehicleImg(Byte[] vehicleImg) {
        this.vehicleImg = vehicleImg;
    }
}
