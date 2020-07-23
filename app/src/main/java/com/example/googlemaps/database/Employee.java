package com.example.googlemaps.database;

public class Employee {

    private int id;
    private String name;
    private String dateOfBirth;
    private int hasCar;
    private String address;
    private double latitude;
    private double longitude;

    public Employee( int id, String name, String dateOfBirth, int hasCar, String address, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.hasCar = hasCar;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getHasCar() {
        return hasCar;
    }

    public void setHasCar(int hasCar) {
        this.hasCar = hasCar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
