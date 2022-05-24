package com.illumlg.transport_control.entity.station;

import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Train;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "train_stations")
public class TrainStation extends Station<Train> {

    public TrainStation(String name, String location, int parkingPlaces) {
        super(name, location, parkingPlaces);
    }

    protected TrainStation() {

    }
}
