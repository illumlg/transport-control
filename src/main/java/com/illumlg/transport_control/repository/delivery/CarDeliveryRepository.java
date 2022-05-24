package com.illumlg.transport_control.repository.delivery;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDeliveryRepository extends DeliveryRepository<CarDelivery> {

}
