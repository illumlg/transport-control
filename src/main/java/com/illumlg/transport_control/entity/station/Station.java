package com.illumlg.transport_control.entity.station;

import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.entity.transport.Transport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MappedSuperclass
public abstract class Station<T extends Transport<? extends Station<T>>> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String location;
    protected int parkingPlaces;

    @OneToMany(fetch = FetchType.EAGER)
    protected final List<T> transports = new ArrayList<>();

    protected Station() {

    }

    protected Station(String name, String location, int parkingPlaces) {
        this.name = name;
        this.location = location;
        this.parkingPlaces = parkingPlaces;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getParkingPlaces() {
        return parkingPlaces;
    }

    public void addTransport(T t) {
        if(parkingPlaces>0) {
            transports.add(t);
            parkingPlaces--;
        }
    }

    public void removeTransport(T t) {
        if(transports.remove(t))
            parkingPlaces++;
    }

    public List<T> getTransports() {
        return Collections.unmodifiableList(transports);
    }
}
