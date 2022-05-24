package com.illumlg.transport_control.controller.station;

import com.illumlg.transport_control.dto.StationDTO;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.PlaneDelivery;
import com.illumlg.transport_control.entity.station.Airport;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transportable.Cargo;
import com.illumlg.transport_control.entity.transportable.Passenger;
import com.illumlg.transport_control.service.delivery.DeliveryService;
import com.illumlg.transport_control.service.delivery.PlaneDeliveryService;
import com.illumlg.transport_control.service.station.AirportService;
import com.illumlg.transport_control.service.transport.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController extends StationController<Airport, Plane, PlaneDelivery> {

    public AirportController(AirportService stationService, PlaneService transportService, PlaneDeliveryService deliveryService) {
        super(stationService, transportService, deliveryService);
    }

    @GetMapping("/plane")
    public List<Airport> getAirports() {
        return stationService.getAllStations();
    }

    @PostMapping("/plane")
    public void createAirport(@RequestBody StationDTO stationDTO) {
        stationService.createStation(new Airport(
                stationDTO.getName(),
                stationDTO.getLocation(),
                stationDTO.getParkingPlaces())
        );
    }

    @GetMapping("/plane/deliveries")
    @Override
    public List<PlaneDelivery> getDeliveries() {
        return super.getDeliveries();
    }

    @PostMapping("/plane/createCargoDelivery")
    @Override
    public ResponseEntity<Void> createCargoDelivery(@RequestParam long stationId,
                                              @RequestParam long destinationId,
                                              @RequestParam long transportId,
                                              @RequestBody List<Cargo> deliverable) {
        return super.createCargoDelivery(stationId, destinationId, transportId, deliverable);
    }

    @PostMapping("/plane/createPassengerDelivery")
    @Override
    public ResponseEntity<Void> createPassengerDelivery(@RequestParam long stationId,
                                        @RequestParam long destinationId,
                                        @RequestParam long transportId,
                                        @RequestBody List<Passenger> deliverable) {
        return super.createPassengerDelivery(stationId, destinationId, transportId, deliverable);
    }
}
