package com.illumlg.transport_control.controller.transport;

import com.illumlg.transport_control.dto.FreightTransportDTO;
import com.illumlg.transport_control.dto.PassengerTransportDTO;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.service.station.SeaportService;
import com.illumlg.transport_control.service.transport.ShipService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipController extends TransportController<Seaport, Ship> {

    public ShipController(ShipService transportService, SeaportService stationService) {
        super(transportService, stationService);
    }

    @GetMapping("/ship")
    @Override
    public List<Ship> getAllTransport() {
        return transportService.getAllTransport();
    }

    @GetMapping("/ship/paged")
    @Override
    public Page<Ship> getAllTransportPaginated(int size, int page) {
        return transportService.getAllTransportPaginated(size, page);
    }

    @PostMapping("/ship/freight")
    @Override
    public void createFreightTransport(@RequestBody FreightTransportDTO transportDto, @RequestParam long stationId) {
        Seaport sp = stationService.getStationById(stationId);
        if(sp != null) {
            Ship s = new Ship(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(s);
            sp.addTransport(s);
            stationService.createStation(sp);
        }
    }

    @PostMapping("/ship/passenger")
    @Override
    public void createPassengerTransport(@RequestBody PassengerTransportDTO transportDto, @RequestParam long stationId) {
        Seaport sp = stationService.getStationById(stationId);
        if(sp != null) {
            Ship s = new Ship(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(s);
            sp.addTransport(s);
            stationService.createStation(sp);
        }
    }

    @PostMapping("/ship/unload")
    @Override
    public void unloadCargo(@RequestParam long id) {
        super.unloadCargo(id);
    }
}
