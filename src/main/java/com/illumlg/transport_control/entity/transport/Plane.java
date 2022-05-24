package com.illumlg.transport_control.entity.transport;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.PlaneDelivery;
import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.transportable.Transportable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Plane extends Transport<Airport> {
    public Plane(String name, Transportable content) {
        super(name, content);
    }

    protected Plane() {

    }

    @Override
    public Delivery<Plane, Airport> deliver(Airport source, Airport destination) {
        PlaneDelivery d = new PlaneDelivery(source, destination, this);
        source.removeTransport(this);
        this.onStation = false;
        return d;
    }
}
