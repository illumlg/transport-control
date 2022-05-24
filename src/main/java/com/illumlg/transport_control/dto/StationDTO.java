package com.illumlg.transport_control.dto;

public class StationDTO {
    private String name;
    private String location;
    private int parkingPlaces;

    public StationDTO() {

    }
    public StationDTO(String name, String location, int parkingPlaces) {
        this.name = name;
        this.location = location;
        this.parkingPlaces = parkingPlaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(int parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }
}
