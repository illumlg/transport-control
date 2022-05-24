package com.illumlg.transport_control.controller.transport;

import com.illumlg.transport_control.dto.FreightTransportDTO;
import com.illumlg.transport_control.dto.PassengerTransportDTO;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.service.station.CarStationService;
import com.illumlg.transport_control.service.transport.CarService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController extends TransportController<CarStation, Car> {

    public CarController(CarService transportService, CarStationService stationService) {
        super(transportService, stationService);
    }

    @GetMapping("/car")
    @Override
    public List<Car> getAllTransport() {
        return transportService.getAllTransport();
    }

    @GetMapping("/car/paged")
    @Override
    public Page<Car> getAllTransportPaginated(int size, int page) {
        return transportService.getAllTransportPaginated(size, page);
    }

    @PostMapping("/car/freight")
    @Override
    public void createFreightTransport(@RequestBody FreightTransportDTO transportDto, @RequestParam long stationId) {
        CarStation ts = stationService.getStationById(stationId);
        if(ts != null) {
            Car t = new Car(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(t);
            ts.addTransport(t);
            stationService.createStation(ts);
        }
    }

    @PostMapping("/car/passenger")
    @Override
    public void createPassengerTransport(@RequestBody PassengerTransportDTO transportDto, @RequestParam long stationId) {
        CarStation cs = stationService.getStationById(stationId);
        if(cs != null) {
            Car c = new Car(transportDto.getName(), transportDto.getContent());
            transportService.createTransport(c);
            cs.addTransport(c);
            stationService.createStation(cs);
        }
    }

    @PostMapping("/car/unload")
    @Override
    public void unloadCargo(@RequestParam long id) {
        super.unloadCargo(id);
    }
}
