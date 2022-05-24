package com.illumlg.transport_control.service.delivery;

import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.delivery.CarDeliveryRepository;
import com.illumlg.transport_control.repository.delivery.DeliveryRepository;
import com.illumlg.transport_control.repository.delivery.TrainDeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDeliveryService implements DeliveryService<CarDelivery> {
    private final CarDeliveryRepository repository;

    public CarDeliveryService(CarDeliveryRepository repository) {
        this.repository = repository;
    }

    public void createDelivery(CarDelivery d) {
        repository.save(d);
    }

    public void deleteDelivery(CarDelivery d) {
        repository.delete(d);
    }

    public List<CarDelivery> getAllDeliveries() {
        return repository.findAll();
    }

    public CarDelivery getDeliveryById(long id) {
        return repository.findById(id).orElse(null);
    }
}
