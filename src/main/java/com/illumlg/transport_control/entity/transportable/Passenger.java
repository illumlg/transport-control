package com.illumlg.transport_control.entity.transportable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passenger implements Transportable.Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String passport;
    private Class passengerClass;
    private boolean privileges;

    public Passenger(String name, String passport, Class passengerClass, boolean privileges) {
        this.name = name;
        this.passport = passport;
        this.passengerClass = passengerClass;
        this.privileges = privileges;
    }

    protected Passenger() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public boolean isPrivileges() {
        return privileges;
    }

    public Class getPassengerClass() {
        return passengerClass;
    }

    enum Class {
        FIRST, SECOND, THIRD;
    }
}
