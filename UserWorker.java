package com.example.cargotracker;

public class UserWorker {

    public String fullname, icnumber, phonenumber, email, staffid, port, department, wharf;

    public UserWorker() {
    }

    public UserWorker(String fullname, String icnumber, String phonenumber, String email, String staffid, String port, String department, String wharf) {
        this.fullname = fullname;
        this.icnumber = icnumber;
        this.phonenumber = phonenumber;
        this.email = email;
        this.staffid = staffid;
        this.port = port;
        this.department = department;
        this.wharf = wharf;
    }

    public String getFullname() {
        return fullname;
    }

    public String getIcnumber() {
        return icnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getStaffid() {
        return staffid;
    }

    public String getPort() {
        return port;
    }

    public String getDepartment() {
        return department;
    }

    public String getWharf() {
        return wharf;
    }
}
