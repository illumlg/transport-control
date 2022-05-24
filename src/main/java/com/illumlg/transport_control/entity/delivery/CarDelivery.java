package com.illumlg.transport_control.entity.delivery;

import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;

import javax.persistence.Entity;

@Entity
public class CarDelivery extends Delivery<Car, CarStation> {

    public CarDelivery(CarStation source, CarStation destination, Car executor) {
        super(source, destination, executor);
    }

    public CarDelivery() {
    }

    @Override
    public int getEstimatedTime() {
        return 1;
    }
}
