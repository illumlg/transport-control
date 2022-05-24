package com.illumlg.transport_control.service.delivery;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import com.illumlg.transport_control.repository.delivery.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeliveryService<D extends Delivery<? extends Transport<?>, ? extends Station<?>>> {
    void createDelivery(D d);
    void deleteDelivery(D d);
    List<D> getAllDeliveries();
    D getDeliveryById(long id);
}
