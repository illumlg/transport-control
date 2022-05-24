package com.illumlg.transport_control.entity.transportable;

import javax.persistence.*;

@Entity
@Table(name = "transportables")
@DiscriminatorColumn(name="type")
public abstract class BaseTransportable implements Transportable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected BaseTransportable() {

    }

    public Long getId() {
        return id;
    }
}
