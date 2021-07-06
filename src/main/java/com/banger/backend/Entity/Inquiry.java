package com.banger.backend.Entity;

import javax.persistence.*;

@Entity
@Table(name = "inquiry")
public class Inquiry {
    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inquiryId;

    @Column(name = "inquirer_name")
    private String inquirerName;

    @Column(name = "inquirer_email")
    private String inquirerEmail;

    @Column(name = "contact")
    private String contactNum;

    @Column(name = "message")
    private String message;


    public Inquiry(int inquiryId, String inquirerName, String inquirerEmail, String contactNum, String message) {
        this.inquiryId = inquiryId;
        this.inquirerName = inquirerName;
        this.inquirerEmail = inquirerEmail;
        this.contactNum = contactNum;
        this.message = message;
    }

    public Inquiry() {

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
