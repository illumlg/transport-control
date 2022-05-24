package com.illumlg.transport_control.service.delivery;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.delivery.CarDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.TrainDeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainDeliveryService implements DeliveryService<TrainDelivery> {
    private final TrainDeliveryRepository repository;

    public TrainDeliveryService(TrainDeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(TrainDelivery d) {
        repository.save(d);
    }

    public void deleteDelivery(TrainDelivery d) {
        repository.delete(d);
    }

    public List<TrainDelivery> getAllDeliveries() {
        return repository.findAll();
    }

    public TrainDelivery getDeliveryById(long id) {
        return repository.findById(id).orElse(null);
    }
}
