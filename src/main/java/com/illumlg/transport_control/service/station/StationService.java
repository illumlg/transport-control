package com.illumlg.transport_control.service.station;

import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;

import java.util.List;

public interface StationService<S extends Station<? extends Transport<S>>> {
    void createStation(S station);
    List<S> getAllStations();
    S getStationById(long id);
}
