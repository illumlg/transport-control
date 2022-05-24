package com.illumlg.transport_control.repository.transport;

import com.illumlg.transport_control.entity.transport.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends TransportRepository<Train> {
    @Query("SELECT t from Train t WHERE t.name = ?1")
    Train findTrainByName(String name);

    @Query(value = "SELECT t FROM Train t ORDER BY t.id")
    Page<Train> findAllTrainsPaginated(Pageable pageable);
}
