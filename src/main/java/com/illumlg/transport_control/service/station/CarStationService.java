package com.illumlg.transport_control.service.station;

import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.repository.station.AirportRepository;
import com.illumlg.transport_control.repository.station.CarStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStationService implements StationService<CarStation> {
    private final CarStationRepository repository;

    public CarStationService(CarStationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(CarStation t) {
        repository.save(t);
    }

    @Override
    public List<CarStation> getAllStations() {
        return repository.findAll();
    }

    @Override
    public CarStation getStationById(long id) {
        return repository.findById(id).orElse(null);
    }
}
