package com.example.cargotracker;

public class dutyScheduleModel {

    String  dutydetails, dutyschedule, firebaseid,  managername, shipneedtobehandled, staffname,  wharfname ;

    public dutyScheduleModel() {
    }

    public dutyScheduleModel(String dutydetails, String dutyschedule, String firebaseid, String managername, String shipneedtobehandled, String staffname, String wharfname) {
        this.dutydetails = dutydetails;
        this.dutyschedule = dutyschedule;
        this.firebaseid = firebaseid;
        this.managername = managername;
        this.shipneedtobehandled = shipneedtobehandled;
        this.staffname = staffname;
        this.wharfname = wharfname;
    }


    public String getDutydetails() {
        return dutydetails;
    }

    public String getDutyschedule() {
        return dutyschedule;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public String getManagername() {
        return managername;
    }

    public String getShipneedtobehandled() {
        return shipneedtobehandled;
    }

    public String getStaffname() {
        return staffname;
    }

    public String getWharfname() {
        return wharfname;
    }
}
