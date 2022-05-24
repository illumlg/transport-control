package com.illumlg.transport_control;

import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.entity.transport.Train;
import com.illumlg.transport_control.entity.transportable.CargoContainer;
import com.illumlg.transport_control.entity.transportable.PassengerContainer;
import com.illumlg.transport_control.service.transport.CarService;
import com.illumlg.transport_control.service.transport.PlaneService;
import com.illumlg.transport_control.service.transport.ShipService;
import com.illumlg.transport_control.service.transport.TrainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransportControlApplicationTests {
    private final MockMvc mvc;
    private Train t;
    private Car c;
    private Ship s;
    private Plane p;
    private final TrainService trainService;
    private final CarService carService;
    private final ShipService shipService;
    private final PlaneService planeService;

    @Autowired
    TransportControlApplicationTests(TrainService trainService,
                                     CarService carService,
                                     PlaneService planeService,
                                     ShipService shipService,
                                     MockMvc mvc) {
        this.trainService = trainService;
        this.carService = carService;
        this.planeService = planeService;
        this.shipService = shipService;
        this.mvc = mvc;
    }

    @BeforeAll
    void setup() {
        t = new Train("LT-237M", new CargoContainer(1000));
        c = new Car("Iveco-SM", new CargoContainer(100));
        s = new Ship("Princess of the Sea", new PassengerContainer(2000, 3000, 5000));
        p = new Plane("Boeing-737", new PassengerContainer(100, 100, 100));
        carService.createTransport(c);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testFindTrainByName() {
        trainService.createTransport(t);
        Assertions.assertEquals(trainService.getTransportByName("LT-237M"), t);
    }

    @Test
    void testGetAllTrains() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transport/train")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("LT-237M"));
    }

    @Test
    void testFindCarByName() {
        Assertions.assertEquals(carService.getTransportByName("Iveco-SM"), c);
    }

    @Test
    void testGetAllCars() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transport/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Iveco-SM"));
    }

    @Test
    void testFindPlaneByName() {
        planeService.createTransport(p);
        Assertions.assertEquals(planeService.getTransportByName("Boeing-737"), p);
    }

    @Test
    void testGetAllPlanes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transport/plane")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Boeing-737"));
    }

    @Test
    void testFindShipByName() {
        shipService.createTransport(s);
        Assertions.assertEquals(shipService.getTransportByName("Princess of the Sea"), s);
    }

    @Test
    void testGetAllShips() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transport/ship")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Princess of the Sea"));
    }
}
