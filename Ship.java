package com.example.cargotracker;

public class Ship {

    String scnnumber,firebaseidshiptime, vesselid, vesselname, entrypointspinner, locationn, terminalspinner, agentname, arrivaldate;

    public Ship() {
    }

    public Ship(String scnnumber, String firebaseidshiptime, String vesselid, String vesselname, String entrypointspinner, String locationn, String terminalspinner, String agentname, String arrivaldate) {
        this.scnnumber = scnnumber;
        this.firebaseidshiptime = firebaseidshiptime;
        this.vesselid = vesselid;
        this.vesselname = vesselname;
        this.entrypointspinner = entrypointspinner;
        this.locationn = locationn;
        this.terminalspinner = terminalspinner;
        this.agentname = agentname;
        this.arrivaldate = arrivaldate;
    }

    public String getScnnumber() {

        return scnnumber;
    }

    public String getFirebaseidshiptime() {
        return firebaseidshiptime;
    }

    public String getVesselid() {

        return vesselid;
    }

    public String getVesselname() {

        return vesselname;
    }

    public String getEntrypointspinner() {

        return entrypointspinner;
    }

    public String getLocationn() {

        return locationn;
    }

    public String getTerminalspinner() {

        return terminalspinner;
    }

    public String getAgentname() {

        return agentname;
    }

    public String getArrivaldate() {

        return arrivaldate;
    }
}
