package com.illumlg.transport_control.controller.station;

import com.illumlg.transport_control.dto.StationDTO;
import com.illumlg.transport_control.entity.delivery.CarDelivery;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transportable.Cargo;
import com.illumlg.transport_control.entity.transportable.Passenger;
import com.illumlg.transport_control.service.delivery.CarDeliveryService;
import com.illumlg.transport_control.service.delivery.DeliveryService;
import com.illumlg.transport_control.service.station.CarStationService;
import com.illumlg.transport_control.service.transport.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarStationController extends StationController<CarStation, Car, CarDelivery> {

    public CarStationController(CarStationService stationService, CarService transportService, CarDeliveryService deliveryService) {
        super(stationService, transportService, deliveryService);
    }

    @GetMapping("/car")
    public List<CarStation> getCarStations() {
        return stationService.getAllStations();
    }

    @PostMapping("/car")
    public void createCarStation(@RequestBody StationDTO stationDTO) {
        stationService.createStation(new CarStation(
                stationDTO.getName(),
                stationDTO.getLocation(),
                stationDTO.getParkingPlaces())
        );
    }

    @GetMapping("/car/deliveries")
    @Override
    public List<CarDelivery> getDeliveries() {
        return super.getDeliveries();
    }

    @PostMapping("/car/createCargoDelivery")
    @Override
    public ResponseEntity<Void> createCargoDelivery(@RequestParam long stationId,
                                              @RequestParam long destinationId,
                                              @RequestParam long transportId,
                                              @RequestBody List<Cargo> deliverable) {
        return super.createCargoDelivery(stationId, destinationId, transportId, deliverable);
    }

    @PostMapping("/car/createPassengerDelivery")
    @Override
    public ResponseEntity<Void> createPassengerDelivery(@RequestParam long stationId,
                                        @RequestParam long destinationId,
                                        @RequestParam long transportId,
                                        @RequestBody List<Passenger> deliverable) {
        return super.createPassengerDelivery(stationId, destinationId, transportId, deliverable);
    }
}
