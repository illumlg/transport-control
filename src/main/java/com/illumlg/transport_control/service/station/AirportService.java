package com.illumlg.transport_control.service.station;

import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.repository.station.AirportRepository;
import com.illumlg.transport_control.repository.station.SeaportRepository;
import com.illumlg.transport_control.repository.station.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService implements StationService<Airport> {
    private final AirportRepository repository;

    public AirportService(AirportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(Airport t) {
        repository.save(t);
    }

    @Override
    public List<Airport> getAllStations() {
        return repository.findAll();
    }

    @Override
    public Airport getStationById(long id) {
        return repository.findById(id).orElse(null);
    }
}
