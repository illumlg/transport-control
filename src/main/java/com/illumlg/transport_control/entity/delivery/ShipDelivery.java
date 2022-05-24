package com.illumlg.transport_control.entity.delivery;

import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.transport.Ship;

import javax.persistence.Entity;

@Entity
public class ShipDelivery extends Delivery<Ship, Seaport> {
    public ShipDelivery(Seaport source, Seaport destination, Ship executor) {
        super(source, destination, executor);
    }

    public ShipDelivery() {
    }

    @Override
    public int getEstimatedTime() {
        return 1;
    }
}
