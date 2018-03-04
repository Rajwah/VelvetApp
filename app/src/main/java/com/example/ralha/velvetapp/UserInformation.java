package com.example.ralha.velvetapp;

/**
 * Created by ShadenMHD on 2/18/18.
 */

public class UserInformation {
    private String name;
    private String address;
    private String CCN;
    private String CCV;
    private String nameOnCard;
    private String phoneNo;
    private String EndDate;

    public UserInformation() {
    }


    public UserInformation(String name, String address, String CCN, String CCV, String nameOnCard, String EndDate, String phoneNo) {
        this.setName(name);
        this.setAddress(address);
        this.setCCN(CCN);
        this.setCCV(CCV);
        this.setNameOnCard(nameOnCard);
        this.setEndDate(EndDate);
        this.setPhoneNo(phoneNo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCCN() {
        return CCN;
    }

    public void setCCN(String CCN) {
        this.CCN = CCN;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
