package com.banger.backend.DTO;

public class searchVehicleDTO {

    private String pickupTime;
    private String returnTime;

    public searchVehicleDTO(String pickupTime, String returnTime) {
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
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
}
