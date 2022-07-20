package com.example.cargotracker;

public class StaffScheduleDetails {
    String dutyID, dutydetails, managername, wharfname, shipneedtobehandled;

    public StaffScheduleDetails() {
    }

    public StaffScheduleDetails(String dutyID, String dutydetails, String managername, String wharfname, String shipneedtobehandled) {
        this.dutyID = dutyID;
        this.dutydetails = dutydetails;
        this.managername = managername;
        this.wharfname = wharfname;
        this.shipneedtobehandled = shipneedtobehandled;
    }

}
