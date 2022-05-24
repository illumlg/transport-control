package com.illumlg.transport_control.repository.delivery;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository<D extends Delivery<? extends Transport<?>,? extends Station<?>>> extends JpaRepository<D, Long> {

}
