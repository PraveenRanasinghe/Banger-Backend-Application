package com.banger.backend.Scraping;

import java.io.Serializable;

public class WebPageContent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vehicleType;
    private String pricePerDay;

    public WebPageContent() {
    }

    public WebPageContent(String vehicleType, String pricePerDay) {
        super();
        this.vehicleType = vehicleType;
        this.pricePerDay = pricePerDay;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
