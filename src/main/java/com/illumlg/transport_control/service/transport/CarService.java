package com.illumlg.transport_control.service.transport;

import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.transport.CarRepository;
import com.illumlg.transport_control.repository.transport.TrainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements TransportService<Car> {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTransport(Car t) {
        repository.save(t);
    }

    @Override
    public List<Car> getAllTransport() {
        return repository.findAll();
    }

    @Override
    public Page<Car> getAllTransportPaginated(int pageSize, int pageNumber) {
        return repository.findAllCarsPaginated(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public Car getTransportById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Car getTransportByName(String name) {
        return repository.findCarByName(name);
    }
}
