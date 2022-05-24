package com.illumlg.transport_control.service.station;

import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.repository.station.TrainStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainStationService implements StationService<TrainStation> {
    private final TrainStationRepository repository;

    public TrainStationService(TrainStationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(TrainStation t) {
        repository.save(t);
    }

    @Override
    public List<TrainStation> getAllStations() {
        return repository.findAll();
    }

    @Override
    public TrainStation getStationById(long id) {
        return repository.findById(id).orElse(null);
    }
}
