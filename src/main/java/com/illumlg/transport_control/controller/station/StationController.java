package com.illumlg.transport_control.controller.station;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;
import com.illumlg.transport_control.entity.transportable.Cargo;
import com.illumlg.transport_control.entity.transportable.CargoContainer;
import com.illumlg.transport_control.entity.transportable.Passenger;
import com.illumlg.transport_control.entity.transportable.PassengerContainer;
import com.illumlg.transport_control.service.delivery.DeliveryService;
import com.illumlg.transport_control.service.station.StationService;
import com.illumlg.transport_control.service.transport.TransportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequestMapping("/station")
public abstract class StationController<S extends Station<T>, T extends Transport<S>, D extends Delivery<T, S>> {
    protected final StationService<S> stationService;
    protected final TransportService<T> transportService;
    protected final DeliveryService<D> deliveryService;
    private final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public StationController(StationService<S> stationService, TransportService<T> transportService, DeliveryService<D> deliveryService) {
        this.stationService = stationService;
        this.transportService = transportService;
        this.deliveryService = deliveryService;
    }

    public List<D> getDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    public ResponseEntity<Void> createCargoDelivery(long stationId, long destinationId, long transportId, List<Cargo> deliverable) {
        S station = stationService.getStationById(stationId);
        S destination = stationService.getStationById(destinationId);
        if(station == null || destination == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        T transport = transportService.getTransportById(transportId);
        if(transport == null || !station.getTransports().contains(transport))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CargoContainer cc = (CargoContainer) transport.getContent();
        cc.addCargos(deliverable.toArray(Cargo[]::new));
        D delivery = (D) transport.deliver(station, destination);
        deliveryService.createDelivery(delivery);
        ses.schedule(() -> {
            delivery.getDestination().addTransport(delivery.getExecutor());
            stationService.createStation(delivery.getDestination());
            delivery.getExecutor().setOnStation(true);
            transportService.createTransport(delivery.getExecutor());
            deliveryService.deleteDelivery(delivery);
            System.out.println("finished");
        }, 5L*delivery.getEstimatedTime(), TimeUnit.SECONDS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> createPassengerDelivery(long stationId, long destinationId, long transportId, List<Passenger> deliverable) {
        S station = stationService.getStationById(stationId);
        S destination = stationService.getStationById(destinationId);
        if(station == null || destination == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        T transport = transportService.getTransportById(transportId);
        if(transport == null || !station.getTransports().contains(transport))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PassengerContainer pc = (PassengerContainer) transport.getContent();
        pc.registerPassengers(deliverable.toArray(Passenger[]::new));
        D delivery = (D) transport.deliver(station, destination);
        deliveryService.createDelivery(delivery);
        ses.schedule(() -> {
            delivery.getDestination().addTransport(delivery.getExecutor());
            stationService.createStation(delivery.getDestination());
            delivery.getExecutor().setOnStation(true);
            transportService.createTransport(delivery.getExecutor());
            deliveryService.deleteDelivery(delivery);
            System.out.println("finished");
        }, 5, TimeUnit.SECONDS);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
