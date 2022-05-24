package com.illumlg.transport_control.repository.station;

import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.Seaport;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends StationRepository<Airport> {

}
