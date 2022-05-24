package com.illumlg.transport_control.repository.station;

import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository<S extends Station<? extends Transport<S>>> extends JpaRepository<S, Long> {

}
