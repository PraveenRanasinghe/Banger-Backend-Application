package com.banger.backend.Scraping;

public class Scrapper {
    private String vehicleType;
    private String ratePerMonth;
    private String ratePerWeek;
    private String excessPrice;

    public Scrapper() {
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRatePerMonth() {
        return ratePerMonth;
    }

    public void setRatePerMonth(String ratePerMonth) {
        this.ratePerMonth = ratePerMonth;
    }

    public String getRatePerWeek() {
        return ratePerWeek;
    }

    public void setRatePerWeek(String ratePerWeek) {
        this.ratePerWeek = ratePerWeek;
    }

    public String getExcessPrice() {
        return excessPrice;
    }

    public void setExcessPrice(String excessPrice) {
        this.excessPrice = excessPrice;
    }
}
