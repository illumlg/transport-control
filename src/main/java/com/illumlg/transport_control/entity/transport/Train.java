package com.illumlg.transport_control.entity.transport;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transportable.Transportable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trains")
public class Train extends Transport<TrainStation> {

    public Train(String name, Transportable content) {
        super(name, content);
    }

    protected Train() {

    }

    @Override
    public Delivery<Train, TrainStation> deliver(TrainStation source, TrainStation destination) {
        TrainDelivery d = new TrainDelivery(source, destination, this);
        source.removeTransport(this);
        this.onStation = false;
        return d;
    }
}
