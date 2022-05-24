package com.illumlg.transport_control.repository.delivery;

import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Train;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainDeliveryRepository extends DeliveryRepository<TrainDelivery> {

}
