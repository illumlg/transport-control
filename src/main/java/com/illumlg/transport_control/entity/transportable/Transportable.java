package com.illumlg.transport_control.entity.transportable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.List;

public interface Transportable {
    int getCapacity();
    List<? extends Item> getContent();
    void unload();

    interface Item { }
}
