package com.boiz.covidcasecounter;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    private String statename;
    private ArrayList<String> districtname;
    private ArrayList<Integer> confirmed;
    private ArrayList<Integer> death;
    private ArrayList<Integer> recover;
    private ArrayList<Integer> deltaconfirmed;
    private ArrayList<Integer> deltadeath;
    private ArrayList<Integer> deltarecover;

    public Data() {
    }

    public Data(String statename, ArrayList<String> districtname, ArrayList<Integer> confirmed, ArrayList<Integer> death, ArrayList<Integer> recover
            , ArrayList<Integer> deltaconfirmed, ArrayList<Integer> deltadeath, ArrayList<Integer> deltarecover) {
        this.statename = statename;
        this.districtname = districtname;
        this.confirmed = confirmed;
        this.death = death;
        this.recover = recover;
        this.deltaconfirmed = deltaconfirmed;
        this.deltadeath = deltadeath;
        this.deltarecover = deltarecover;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public ArrayList<String> getDistrictname() {
        return districtname;
    }

    public void setDistrictname(ArrayList<String> districtname) {
        this.districtname = districtname;
    }

    public ArrayList<Integer> getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(ArrayList<Integer> confirmed) {
        this.confirmed = confirmed;
    }

    public ArrayList<Integer> getDeath() {
        return death;
    }

    public void setDeath(ArrayList<Integer> death) {
        this.death = death;
    }

    public ArrayList<Integer> getRecover() {
        return recover;
    }

    public void setRecover(ArrayList<Integer> recover) {
        this.recover = recover;
    }

    public ArrayList<Integer> getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(ArrayList<Integer> deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public ArrayList<Integer> getDeltadeath() {
        return deltadeath;
    }

    public void setDeltadeath(ArrayList<Integer> deltadeath) {
        this.deltadeath = deltadeath;
    }

    public ArrayList<Integer> getDeltarecover() {
        return deltarecover;
    }

    public void setDeltarecover(ArrayList<Integer> deltarecover) {
        this.deltarecover = deltarecover;
    }

    @Override
    public String toString() {
        return "Data{" +
                "statename='" + statename + '\'' +
                ", districtname=" + districtname +
                ", confirmed=" + confirmed +
                ", death=" + death +
                ", recover=" + recover +
                ", deltaconfirmed=" + deltaconfirmed +
                ", deltadeath=" + deltadeath +
                ", deltarecover=" + deltarecover +
                '}';
    }
}
