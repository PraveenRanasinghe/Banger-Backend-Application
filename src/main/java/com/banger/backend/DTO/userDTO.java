package com.banger.backend.DTO;

public class userDTO {

    private String email;
    private String fName;
    private String lName;

    private String dob;
    private String password;
    private String userRole;
    private String contactNum;
    private String isBlackListed;
    private String nicNumber;
    private byte[] licenceImg;
    private byte[] profileImage;
    private byte[] utilityBill;
    private String status;


    public userDTO() {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public String getIsBlackListed() {
        return isBlackListed;
    }

    public void setIsBlackListed(String isBlackListed) {
        this.isBlackListed = isBlackListed;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
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
