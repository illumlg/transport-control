package com.illumlg.transport_control.controller.station;

import com.illumlg.transport_control.dto.StationDTO;
import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.delivery.TrainDelivery;
import com.illumlg.transport_control.entity.station.TrainStation;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.entity.transportable.Cargo;
import com.illumlg.transport_control.entity.transportable.Passenger;
import com.illumlg.transport_control.service.delivery.DeliveryService;
import com.illumlg.transport_control.service.delivery.TrainDeliveryService;
import com.illumlg.transport_control.service.station.TrainStationService;
import com.illumlg.transport_control.service.transport.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainStationController extends StationController<TrainStation, Train, TrainDelivery> {

    public TrainStationController(TrainStationService stationService, TrainService transportService, TrainDeliveryService deliveryService) {
        super(stationService, transportService, deliveryService);
    }

    @GetMapping("/train")
    public List<TrainStation> getTrainStations() {
        return stationService.getAllStations();
    }

    @PostMapping("/train")
    public void createTrainStation(@RequestBody StationDTO stationDTO) {
        stationService.createStation(new TrainStation(
                stationDTO.getName(),
                stationDTO.getLocation(),
                stationDTO.getParkingPlaces())
        );
    }

    @GetMapping("/train/deliveries")
    @Override
    public List<TrainDelivery> getDeliveries() {
        return super.getDeliveries();
    }

    @PostMapping("/train/createCargoDelivery")
    @Override
    public ResponseEntity<Void> createCargoDelivery(@RequestParam long stationId,
                                              @RequestParam long destinationId,
                                              @RequestParam long transportId,
                                              @RequestBody List<Cargo> deliverable) {
        return super.createCargoDelivery(stationId, destinationId, transportId, deliverable);
    }

    @PostMapping("/train/createPassengerDelivery")
    @Override
    public ResponseEntity<Void> createPassengerDelivery(@RequestParam long stationId,
                                        @RequestParam long destinationId,
                                        @RequestParam long transportId,
                                        @RequestBody List<Passenger> deliverable) {
        return super.createPassengerDelivery(stationId, destinationId, transportId, deliverable);
    }
}
