package com.illumlg.transport_control.controller.transport;

import com.illumlg.transport_control.dto.FreightTransportDTO;
import com.illumlg.transport_control.dto.PassengerTransportDTO;
import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.service.station.AirportService;
import com.illumlg.transport_control.service.transport.PlaneService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaneController extends TransportController<Airport, Plane> {

    public PlaneController(PlaneService transportService, AirportService stationService) {
        super(transportService, stationService);
    }

    @GetMapping("/plane")
    @Override
    public List<Plane> getAllTransport() {
        return transportService.getAllTransport();
    }

    @GetMapping("/plane/paged")
    @Override
    public Page<Plane> getAllTransportPaginated(int size, int page) {
        return transportService.getAllTransportPaginated(size, page);
    }

    @PostMapping("/plane/freight")
    @Override
    public void createFreightTransport(@RequestBody FreightTransportDTO transportDto, @RequestParam long stationId) {
        Airport ap = stationService.getStationById(stationId);
        if(ap != null) {
            Plane p = new Plane(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(p);
            ap.addTransport(p);
            stationService.createStation(ap);
        }
    }

    @PostMapping("/plane/passenger")
    @Override
    public void createPassengerTransport(@RequestBody PassengerTransportDTO transportDto, @RequestParam long stationId) {
        Airport ts = stationService.getStationById(stationId);
        if(ts != null) {
            Plane t = new Plane(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(t);
            ts.addTransport(t);
            stationService.createStation(ts);
        }
    }

    @PostMapping("/plane/unload")
    @Override
    public void unloadCargo(@RequestParam long id) {
        super.unloadCargo(id);
    }
}
