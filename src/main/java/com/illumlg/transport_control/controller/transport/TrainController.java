package com.illumlg.transport_control.controller.transport;

import com.illumlg.transport_control.dto.FreightTransportDTO;
import com.illumlg.transport_control.dto.PassengerTransportDTO;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.service.station.TrainStationService;
import com.illumlg.transport_control.service.transport.TrainService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainController extends TransportController<TrainStation, Train> {

    public TrainController(TrainService transportService, TrainStationService stationService) {
        super(transportService, stationService);
    }

    @GetMapping("/train")
    @Override
    public List<Train> getAllTransport() {
        return transportService.getAllTransport();
    }

    @GetMapping("/train/paged")
    @Override
    public Page<Train> getAllTransportPaginated(@RequestParam int size, @RequestParam int page) {
        return transportService.getAllTransportPaginated(size, page);
    }

    @PostMapping("/train/freight")
    @Override
    public void createFreightTransport(@RequestBody FreightTransportDTO transportDto, @RequestParam long stationId) {
        TrainStation ts = stationService.getStationById(stationId);
        if(ts != null) {
            Train t = new Train(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(t);
            ts.addTransport(t);
            stationService.createStation(ts);
        }
    }

    @PostMapping("/train/passenger")
    @Override
    public void createPassengerTransport(@RequestBody PassengerTransportDTO transportDto, @RequestParam long stationId) {
        TrainStation ts = stationService.getStationById(stationId);
        if(ts != null) {
            Train t = new Train(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(t);
            ts.addTransport(t);
            stationService.createStation(ts);
        }
    }

    @PostMapping("/train/unload")
    @Override
    public void unloadCargo(@RequestParam long id) {
        super.unloadCargo(id);
    }
}
