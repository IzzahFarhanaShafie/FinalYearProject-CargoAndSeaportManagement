package com.example.cargotracker;

public class stafflistModel {

    String emailprofile, fullname, icnumber, image, phonenumber, port, position, staffid, usertype, wharf;

    public stafflistModel() {
    }

    public stafflistModel(String emailprofile, String fullname, String icnumber, String image, String phonenumber, String port, String position, String staffid, String usertype, String wharf) {
        this.emailprofile = emailprofile;
        this.fullname = fullname;
        this.icnumber = icnumber;
        this.image = image;
        this.phonenumber = phonenumber;
        this.port = port;
        this.position = position;
        this.staffid = staffid;
        this.usertype = usertype;
        this.wharf = wharf;
    }

    public String getEmailprofile() {
        return emailprofile;
    }

    public void setEmailprofile(String emailprofile) {
        this.emailprofile = emailprofile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIcnumber() {
        return icnumber;
    }

    public void setIcnumber(String icnumber) {
        this.icnumber = icnumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getWharf() {
        return wharf;
    }

    public void setWharf(String wharf) {
        this.wharf = wharf;
    }
}
