package com.example.cargotracker;

public class StaffSchedule {

    String dutyID, firebaseid, staffname, dutyschedule, dutydetails, managername, wharfname, shipneedtobehandled;

    public StaffSchedule() {
    }

    public StaffSchedule(String dutyID, String firebaseid, String staffname, String dutyschedule, String dutydetails, String managername, String wharfname, String shipneedtobehandled) {
        this.dutyID = dutyID;
        this.firebaseid = firebaseid;
        this.staffname = staffname;
        this.dutyschedule = dutyschedule;
        this.dutydetails = dutydetails;
        this.managername = managername;
        this.wharfname = wharfname;
        this.shipneedtobehandled = shipneedtobehandled;
    }

    public String getDutyID() {
        return dutyID;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public String getStaffname() {
        return staffname;
    }

    public String getDutyschedule() {
        return dutyschedule;
    }

    public String getDutydetails() {
        return dutydetails;
    }

    public String getManagername() {
        return managername;
    }

    public String getWharfname() {
        return wharfname;
    }

    public String getShipneedtobehandled() {
        return shipneedtobehandled;
    }
}
