package com.banger.backend.DTO;

public class acceptBookingDTO {
    private int bookingId;
    private String status;
    private String email;
    private String isLateReturn;

    public acceptBookingDTO() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsLateReturn() {
        return isLateReturn;
    }

    public void setIsLateReturn(String isLateReturn) {
        this.isLateReturn = isLateReturn;
    }
}
