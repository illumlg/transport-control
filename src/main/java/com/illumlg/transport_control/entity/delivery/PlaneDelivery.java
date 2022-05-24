package com.illumlg.transport_control.entity.delivery;

import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Plane;

import javax.persistence.Entity;

@Entity
public class PlaneDelivery extends Delivery<Plane, Airport> {

    public PlaneDelivery(Airport source, Airport destination, Plane executor) {
        super(source, destination, executor);
    }

    public PlaneDelivery() {
    }

    @Override
    public int getEstimatedTime() {
        return 1;
    }
}
