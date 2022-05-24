package com.illumlg.transport_control.entity.station;

import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.entity.transport.Transport;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "cars_stations")
public class CarStation extends Station<Car> {

    public CarStation(String name, String location, int parkingPlaces) {
        super(name, location, parkingPlaces);
    }

    protected CarStation() {

    }
}
