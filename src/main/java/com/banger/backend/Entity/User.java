package com.banger.backend.Entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "contact_num")
    private String contactNum;

    @Column(name = "nic_number")
    private String nicNumber;

    @Column(name = "is_black_listed")
    private String isBlackListed;

    @Column(name = "licence_img")
    private byte[] licenceImg;

    @Column(name = "profile_image")
    private byte[] profileImage;

    @Column(name = "utility_bill")
    private byte[] utilityBill;

    @Column(name = "status")
    private String status;

    public User(){

    }

    public User(String email, String fName, String lName, Date dob, String password, String userRole, String contactNum, String nicNumber, String isBlackListed, byte[] licenceImg, byte[] profileImage, byte[] utilityBill, String status) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.password = password;
        this.userRole = userRole;
        this.contactNum = contactNum;
        this.nicNumber = nicNumber;
        this.isBlackListed = isBlackListed;
        this.licenceImg = licenceImg;
        this.profileImage = profileImage;
        this.utilityBill = utilityBill;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getIsBlackListed() {
        return isBlackListed;
    }

    public void setIsBlackListed(String isBlackListed) {
        this.isBlackListed = isBlackListed;
    }

    public byte[] getLicenceImg() {
        return licenceImg;
    }

    public void setLicenceImg(byte[] licenceImg) {
        this.licenceImg = licenceImg;
    }


    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public byte[] getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(byte[] utilityBill) {
        this.utilityBill = utilityBill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
