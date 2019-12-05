package com.example.alertquake.model;

public class City {
    String name;
    int counter;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public City(String name, int counter, String message) {
        this.name = name;
        this.counter = counter;
        this.message = message;
    }
}
