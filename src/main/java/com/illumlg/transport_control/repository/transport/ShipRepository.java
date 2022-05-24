package com.illumlg.transport_control.repository.transport;

import com.illumlg.transport_control.entity.transport.Ship;
import com.illumlg.transport_control.entity.transport.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends TransportRepository<Ship> {
    @Query("SELECT s from Ship s WHERE s.name = ?1")
    Ship findShipByName(String name);

    @Query(value = "SELECT s FROM Ship s ORDER BY s.id")
    Page<Ship> findAllShipsPaginated(Pageable pageable);
}
