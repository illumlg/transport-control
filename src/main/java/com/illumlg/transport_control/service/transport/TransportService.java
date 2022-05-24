package com.illumlg.transport_control.service.transport;

import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.entity.transport.Transport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransportService<T extends Transport<? extends Station<T>>> {
    void createTransport(T transport);
    List<T> getAllTransport();
    Page<T> getAllTransportPaginated(int pageSize, int pageNumber);
    T getTransportById(long id);
    T getTransportByName(String name);
}
