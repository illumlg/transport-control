package com.illumlg.transport_control.repository.transport;

import com.illumlg.transport_control.entity.transport.Car;
import com.illumlg.transport_control.entity.transport.Plane;
import com.illumlg.transport_control.entity.transport.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends TransportRepository<Plane> {
    @Query("SELECT p from Plane p WHERE p.name = ?1")
    Plane findPlaneByName(String name);

    @Query(value = "SELECT p FROM Plane p ORDER BY p.id")
    Page<Plane> findAllPlanesPaginated(Pageable pageable);
}
