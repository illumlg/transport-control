package com.illumlg.transport_control.controller.station;

import com.illumlg.transport_control.dto.StationDTO;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.ShipDelivery;
import com.illumlg.transport_control.entity.station.CarStation;
import com.illumlg.transport_control.entity.station.Seaport;
import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.entity.transportable.Cargo;
import com.illumlg.transport_control.entity.transportable.Passenger;
import com.illumlg.transport_control.service.delivery.DeliveryService;
import com.illumlg.transport_control.service.delivery.ShipDeliveryService;
import com.illumlg.transport_control.service.station.SeaportService;
import com.illumlg.transport_control.service.transport.ShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeaportController extends StationController<Seaport, Ship, ShipDelivery> {

    public SeaportController(SeaportService stationService, ShipService transportService, ShipDeliveryService deliveryService) {
        super(stationService, transportService, deliveryService);
    }

    @GetMapping("/ship")
    public List<Seaport> getSeaports() {
        return stationService.getAllStations();
    }

    @PostMapping("/ship")
    public void createSeaport(@RequestBody StationDTO stationDTO) {
        stationService.createStation(new Seaport(
                stationDTO.getName(),
                stationDTO.getLocation(),
                stationDTO.getParkingPlaces())
        );
    }

    @GetMapping("/ship/deliveries")
    @Override
    public List<ShipDelivery> getDeliveries() {
        return super.getDeliveries();
    }

    @PostMapping("/ship/createCargoDelivery")
    @Override
    public ResponseEntity<Void> createCargoDelivery(@RequestParam long stationId,
                                                    @RequestParam long destinationId,
                                                    @RequestParam long transportId,
                                                    @RequestBody List<Cargo> deliverable) {
        return super.createCargoDelivery(stationId, destinationId, transportId, deliverable);
    }

    @PostMapping("/ship/createPassengerDelivery")
    @Override
    public ResponseEntity<Void> createPassengerDelivery(@RequestParam long stationId,
                                                        @RequestParam long destinationId,
                                                        @RequestParam long transportId,
                                                        @RequestBody List<Passenger> deliverable) {
        return super.createPassengerDelivery(stationId, destinationId, transportId, deliverable);
    }
}
