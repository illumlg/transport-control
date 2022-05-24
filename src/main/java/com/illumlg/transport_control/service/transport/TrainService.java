package com.illumlg.transport_control.service.transport;

import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.repository.transport.TrainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService implements TransportService<Train> {
    private final TrainRepository repository;

    public TrainService(TrainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTransport(Train t) {
        repository.save(t);
    }

    @Override
    public List<Train> getAllTransport() {
        return repository.findAll();
    }

    @Override
    public Page<Train> getAllTransportPaginated(int pageSize, int pageNumber) {
        return repository.findAllTrainsPaginated(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public Train getTransportById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Train getTransportByName(String name) {
        return repository.findTrainByName(name);
    }
}
