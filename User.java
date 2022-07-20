package com.example.cargotracker;

public class User {

    public String fullname, icnumber, image, phonenumber, emailprofile, staffid, port, position, wharf;

    public User(){

    }

    public User(String fullname, String icnumber, String image, String phonenumber, String emailprofile, String staffid, String port, String position, String wharf){
        this.fullname = fullname;
        this.icnumber = icnumber;
        this.image = image;
        this.phonenumber = phonenumber;
        this.emailprofile = emailprofile;
        this.staffid = staffid;
        this.port = port;
        this.position = position;
        this.wharf = wharf;
    }

    public String getFullname() {
        return fullname;
    }

    public String getIcnumber() {
        return icnumber;
    }

    public String getImage() {
        return image;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmailprofile() {
        return emailprofile;
    }

    public String getStaffid() {
        return staffid;
    }

    public String getPort() {
        return port;
    }

    public String getPosition() {
        return position;
    }

    public String getWharf() {
        return wharf;
    }
}
