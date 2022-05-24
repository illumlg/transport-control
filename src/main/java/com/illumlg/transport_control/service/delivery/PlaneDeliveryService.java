package com.illumlg.transport_control.service.delivery;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.PlaneDelivery;
import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.delivery.CarDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.PlaneDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.TrainDeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneDeliveryService implements DeliveryService<PlaneDelivery> {
    private final PlaneDeliveryRepository repository;

    public PlaneDeliveryService(PlaneDeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(PlaneDelivery d) {
        repository.save(d);
    }

    public void deleteDelivery(PlaneDelivery d) {
        repository.delete(d);
    }

    public List<PlaneDelivery> getAllDeliveries() {
        return repository.findAll();
    }

    public PlaneDelivery getDeliveryById(long id) {
        return repository.findById(id).orElse(null);
    }
}
