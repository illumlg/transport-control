package com.illumlg.transport_control.entity.delivery;

import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transport.Transport;

import javax.persistence.*;

@MappedSuperclass
public abstract class Delivery<T extends Transport<S>, S extends Station<T>> {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private S source;
    @OneToOne
    private S destination;
    @OneToOne
    private T executor;

    public Delivery(S source,
                    S destination,
                    T executor) {
        this.source = source;
        this.destination = destination;
        this.executor = executor;
    }

    protected Delivery() {

    }

    public long getId() {
        return id;
    }

    public S getSource() {
        return source;
    }

    public S getDestination() {
        return destination;
    }

    public T getExecutor() {
        return executor;
    }

    @Transient
    public abstract int getEstimatedTime();
}
