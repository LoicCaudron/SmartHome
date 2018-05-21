package com.example.einore.smarthome.model;

public class User {

    private int id;
    private String name;
    private int pin;
    private String phone;
    private float T_min;
    private float T_max;
    private float Humidity_min;
    private float Humidity_max;
    private float battery_min;
    private float battery_max;


    public User() {}

    public User(String name, int pin){

        this.name = name;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getT_min() {
        return T_min;
    }

    public void setT_min(float t_min) {
        T_min = t_min;
    }

    public float getT_max() {
        return T_max;
    }

    public void setT_max(float t_max) {
        T_max = t_max;
    }

    public float getHumidity_min() {
        return Humidity_min;
    }

    public void setHumidity_min(float humidity_min) {
        Humidity_min = humidity_min;
    }

    public float getHumidity_max() {
        return Humidity_max;
    }

    public void setHumidity_max(float humidity_max) {
        Humidity_max = humidity_max;
    }

    public float getBattery_min() {
        return battery_min;
    }

    public float getBattery_max() { return battery_max; }

    public void setBattery_min(float battery) {
        this.battery_min = battery;
    }

    public void setBattery_max(float battery_max) { this.battery_max = battery_max; }
}
