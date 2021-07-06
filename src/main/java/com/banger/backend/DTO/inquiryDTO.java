package com.banger.backend.DTO;

public class inquiryDTO {
    private int inquiryId;
    private String inquirerName;
    private String inquirerEmail;
    private String contactNum;
    private String message;

    public inquiryDTO(int inquiryId, String inquirerName, String inquirerEmail, String contactNum, String message) {
        this.inquiryId = inquiryId;
        this.inquirerName = inquirerName;
        this.inquirerEmail = inquirerEmail;
        this.contactNum = contactNum;
        this.message = message;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    public String getInquirerEmail() {
        return inquirerEmail;
    }

    public void setInquirerEmail(String inquirerEmail) {
        this.inquirerEmail = inquirerEmail;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
