package com.illumlg.transport_control.entity.transport;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transportable.Transportable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Transport<CarStation> {

    public Car(String name, Transportable content) {
        super(name, content);
    }

    protected Car() {

    }

    @Override
    public Delivery<Car, CarStation> deliver(CarStation source, CarStation destination) {
        CarDelivery d = new CarDelivery(source, destination, this);
        source.removeTransport(this);
        this.onStation = false;
        return d;
    }
}
