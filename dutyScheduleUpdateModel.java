package com.example.cargotracker;

public class dutyScheduleUpdateModel {

    String firebaseid, dutyID, dutydetails, dutyschedule, managername, staffname, shipneedtobehandled, wharfname ;

    public dutyScheduleUpdateModel() {
    }

    public dutyScheduleUpdateModel(String firebaseid, String dutyID, String dutydetails, String dutyschedule, String managername, String staffname, String shipneedtobehandled, String wharfname) {
        this.firebaseid = firebaseid;
        this.dutyID = dutyID;
        this.dutydetails = dutydetails;
        this.dutyschedule = dutyschedule;
        this.managername = managername;
        this.staffname = staffname;
        this.shipneedtobehandled = shipneedtobehandled;
        this.wharfname = wharfname;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public String getDutyID() {
        return dutyID;
    }

    public String getDutydetails() {
        return dutydetails;
    }

    public String getDutyschedule() {
        return dutyschedule;
    }

    public String getManagername() {
        return managername;
    }

    public String getStaffname() {
        return staffname;
    }

    public String getShipneedtobehandled() {
        return shipneedtobehandled;
    }

    public String getWharfname() {
        return wharfname;
    }
}
