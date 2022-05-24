package com.illumlg.transport_control.entity.transportable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue("cargo")
public class CargoContainer extends BaseTransportable {
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Cargo> cargos = new ArrayList<>();
    private int capacity;

    public CargoContainer(int capacity) {
        this.capacity = capacity;
    }

    protected CargoContainer() {

    }

    public void addCargo(Cargo c) {
        if(cargos.stream().map(Cargo::getMass).reduce(0, Integer::sum)+c.getMass() > capacity)
            return;
        cargos.add(c);
    }

    public void addCargos(Cargo... c) {
        for(var car: c) {
            addCargo(car);
        }
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public List<Cargo> getContent() {
        return Collections.unmodifiableList(cargos);
    }

    @Override
    public void unload() {
        cargos.clear();
    }
}
