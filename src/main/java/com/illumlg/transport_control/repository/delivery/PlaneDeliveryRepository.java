package com.illumlg.transport_control.repository.delivery;

import com.illumlg.transport_control.entity.delivery.PlaneDelivery;
import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.transport.Plane;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneDeliveryRepository extends DeliveryRepository<PlaneDelivery> {

}
