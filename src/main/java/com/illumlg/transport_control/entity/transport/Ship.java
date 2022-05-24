package com.illumlg.transport_control.entity.transport;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.ShipDelivery;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.transportable.Transportable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ships")
public class Ship extends Transport<Seaport> {
    public Ship(String name, Transportable content) {
        super(name, content);
    }

    protected Ship() {

    }

    @Override
    public ShipDelivery deliver(Seaport source, Seaport destination) {
        ShipDelivery d = new ShipDelivery(source, destination, this);
        source.removeTransport(this);
        this.onStation = false;
        return d;
    }
}
