package com.example.alertquake.model;

public class Earthquake {

    private double magnitude;

    private String time;

    private String location;

    public Earthquake(double magnitude, String time, String location) {
        this.magnitude = magnitude;
        this.time = time;
        this.location = location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
