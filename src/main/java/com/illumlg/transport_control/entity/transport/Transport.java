package com.illumlg.transport_control.entity.transport;

import com.illumlg.transport_control.entity.delivery.Delivery;
import com.illumlg.transport_control.entity.station.Station;
import com.illumlg.transport_control.entity.transportable.BaseTransportable;
import com.illumlg.transport_control.entity.transportable.PassengerContainer;
import com.illumlg.transport_control.entity.transportable.Transportable;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Transport<S extends Station<? extends Transport<S>>> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToOne(targetEntity = BaseTransportable.class, cascade = CascadeType.ALL)
    private Transportable content;
    private Type type;
    protected boolean onStation = true;

    protected Transport() {
    }

    protected Transport(String name, Transportable content) {
        this.name = name;
        this.content = content;
        type = content instanceof PassengerContainer ? Type.PASSENGER : Type.FREIGHT;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Transportable getContent() {
        return content;
    }

    public Type getType() {
        return type;
    }

    public boolean isOnStation() {
        return onStation;
    }

    public abstract Delivery<? extends Transport<S>, S> deliver(S source, S destination);

    public void setOnStation(boolean onStation) {
        this.onStation = onStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport<?> transport = (Transport<?>) o;
        return id == transport.id && name.equals(transport.name) && type == transport.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    public enum Type {
        PASSENGER, FREIGHT;
    }
}
