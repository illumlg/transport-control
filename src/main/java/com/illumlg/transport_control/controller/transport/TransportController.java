package com.illumlg.transport_control.controller.transport;

import com.illumlg.transport_control.dto.FreightTransportDTO;
import com.illumlg.transport_control.dto.PassengerTransportDTO;
import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import com.illumlg.transport_control.entity.transportable.Transportable;
import com.illumlg.transport_control.service.station.StationService;
import com.illumlg.transport_control.service.transport.TransportService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transport")
public abstract class TransportController<S extends Station<T>, T extends Transport<S>> {
    protected final TransportService<T> transportService;
    protected final StationService<S> stationService;

    public TransportController(TransportService<T> transportService, StationService<S> stationService) {
        this.transportService = transportService;
        this.stationService = stationService;
    }

    public void unloadCargo(long id) {
        T t = transportService.getTransportById(id);
        if(t!=null) {
            Transportable c = t.getContent();
            c.unload();
            transportService.createTransport(t);
        }
    }

    public abstract List<T> getAllTransport();
    public abstract Page<T> getAllTransportPaginated(int size, int page);
    public abstract void createFreightTransport(FreightTransportDTO transportDto, long stationId);
    public abstract void createPassengerTransport(PassengerTransportDTO transportDto, long stationId);
}
