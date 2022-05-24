package com.illumlg.transport_control.service.delivery;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.ShipDelivery;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.delivery.CarDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.ShipDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.TrainDeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipDeliveryService implements DeliveryService<ShipDelivery> {
    private final ShipDeliveryRepository repository;

    public ShipDeliveryService(ShipDeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(ShipDelivery d) {
        repository.save(d);
    }

    public void deleteDelivery(ShipDelivery d) {
        repository.delete(d);
    }

    public List<ShipDelivery> getAllDeliveries() {
        return repository.findAll();
    }

    public ShipDelivery getDeliveryById(long id) {
        return repository.findById(id).orElse(null);
    }
}
