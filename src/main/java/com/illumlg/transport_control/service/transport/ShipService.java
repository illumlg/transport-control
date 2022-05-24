package com.illumlg.transport_control.service.transport;

import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.repository.transport.ShipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService implements TransportService<Ship> {
    private final ShipRepository repository;

    public ShipService(ShipRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTransport(Ship t) {
        repository.save(t);
    }

    @Override
    public List<Ship> getAllTransport() {
        return repository.findAll();
    }

    @Override
    public Page<Ship> getAllTransportPaginated(int pageSize, int pageNumber) {
        return repository.findAllShipsPaginated(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public Ship getTransportById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Ship getTransportByName(String name) {
        return repository.findShipByName(name);
    }
}
