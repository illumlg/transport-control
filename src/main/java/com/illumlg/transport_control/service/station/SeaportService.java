package com.illumlg.transport_control.service.station;

import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.repository.station.SeaportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeaportService implements StationService<Seaport> {
    private final SeaportRepository repository;

    public SeaportService(SeaportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(Seaport t) {
        repository.save(t);
    }

    @Override
    public List<Seaport> getAllStations() {
        return repository.findAll();
    }

    @Override
    public Seaport getStationById(long id) {
        return repository.findById(id).orElse(null);
    }
}
