package com.illumlg.transport_control.entity.delivery;

import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Train;

import javax.persistence.Entity;

@Entity
public class TrainDelivery extends Delivery<Train, TrainStation> {
    public TrainDelivery(TrainStation source, TrainStation destination, Train executor) {
        super(source, destination, executor);
    }

    public TrainDelivery() {
    }

    @Override
    public int getEstimatedTime() {
        return 2;
    }
}
