package com.illumlg.transport_control.service.transport;

import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.repository.transport.PlaneRepository;
import com.illumlg.transport_control.repository.transport.ShipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneService implements TransportService<Plane> {
    private final PlaneRepository repository;

    public PlaneService(PlaneRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTransport(Plane t) {
        repository.save(t);
    }

    @Override
    public List<Plane> getAllTransport() {
        return repository.findAll();
    }

    @Override
    public Page<Plane> getAllTransportPaginated(int pageSize, int pageNumber) {
        return repository.findAllPlanesPaginated(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public Plane getTransportById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Plane getTransportByName(String name) {
        return repository.findPlaneByName(name);
    }
}
