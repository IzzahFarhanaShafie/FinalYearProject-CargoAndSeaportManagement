package com.example.cargotracker;

public class shipTimeModel {

    String agentname, arrivaldate, entrypointspinner, firebaseidshiptime, locationn, scnnumber, terminalspinner, vesselid, vesselname;

    public shipTimeModel() {
    }

    public shipTimeModel(String agentname, String arrivaldate, String entrypointspinner, String firebaseidshiptime, String locationn, String scnnumber, String terminalspinner, String vesselid, String vesselname) {
        this.agentname = agentname;
        this.arrivaldate = arrivaldate;
        this.entrypointspinner = entrypointspinner;
        this.firebaseidshiptime = firebaseidshiptime;
        this.locationn = locationn;
        this.scnnumber = scnnumber;
        this.terminalspinner = terminalspinner;
        this.vesselid = vesselid;
        this.vesselname = vesselname;
    }

    public String getAgentname() {
        return agentname;
    }

    public String getArrivaldate() {
        return arrivaldate;
    }

    public String getEntrypointspinner() {
        return entrypointspinner;
    }

    public String getFirebaseidshiptime() {
        return firebaseidshiptime;
    }

    public String getLocationn() {
        return locationn;
    }

    public String getScnnumber() {
        return scnnumber;
    }

    public String getTerminalspinner() {
        return terminalspinner;
    }

    public String getVesselid() {
        return vesselid;
    }

    public String getVesselname() {
        return vesselname;
    }
}
