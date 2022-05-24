package com.illumlg.transport_control.entity.station;

import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Train;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport extends Station<Plane> {

    public Airport(String name, String location, int parkingPlaces) {
        super(name, location, parkingPlaces);
    }

    public Airport() {

    }
}
