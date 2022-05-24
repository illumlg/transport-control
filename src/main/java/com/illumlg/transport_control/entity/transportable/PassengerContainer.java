package com.illumlg.transport_control.entity.transportable;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("passenger")
public class PassengerContainer extends BaseTransportable {
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Passenger> passengers = new ArrayList<>();
    @ElementCollection
    private final Map<Passenger.Class, Integer> classCapacities = new HashMap<>();

    public PassengerContainer(int firstClass, int secondClass, int thirdClass) {
        classCapacities.put(Passenger.Class.FIRST, firstClass);
        classCapacities.put(Passenger.Class.SECOND, secondClass);
        classCapacities.put(Passenger.Class.THIRD, thirdClass);
    }

    protected PassengerContainer() {

    }

    public void registerPassenger(Passenger passenger) {
        var pClass = passenger.getPassengerClass();
        var classPassengers = passengers.stream()
                .filter(p -> p.getPassengerClass().equals(pClass)).toList();
        if(classPassengers.size()+1 >= classCapacities.get(pClass))
            return;
        passengers.add(passenger);
    }

    public void registerPassengers(Passenger... p) {
        for(var pas: p) {
            registerPassenger(pas);
        }
    }

    @Override
    public int getCapacity() {
        return classCapacities.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Passenger> getContent() {
        return Collections.unmodifiableList(passengers);
    }

    @Override
    public void unload() {
        passengers.clear();
    }
}
