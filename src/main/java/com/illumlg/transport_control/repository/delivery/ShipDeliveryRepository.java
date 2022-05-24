package com.illumlg.transport_control.repository.delivery;

import com.illumlg.transport_control.entity.delivery.ShipDelivery;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.transport.Ship;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipDeliveryRepository extends DeliveryRepository<ShipDelivery> {

}
