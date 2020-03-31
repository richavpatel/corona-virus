package com.richa.coronavirus.models;

public class Location {

    private String state;
    private String  country;
    private int latestTotalCases;

    public String getState() {
        return state;
    }

    public void setState(String states) {
        this.state = states;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(Integer latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "Location{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases='" + latestTotalCases + '\'' +
                '}';
    }
}
