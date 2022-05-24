package com.illumlg.transport_control.repository.transport;

import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.entity.transport.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends TransportRepository<Car> {
    @Query("SELECT c from Car c WHERE c.name = ?1")
    Car findCarByName(String name);

    @Query(value = "SELECT c FROM Car c ORDER BY c.id")
    Page<Car> findAllCarsPaginated(Pageable pageable);
}
