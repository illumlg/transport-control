package com.illumlg.transport_control.repository.transport;

import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository<T extends Transport<? extends Station<T>>> extends JpaRepository<T, Long> {

}
