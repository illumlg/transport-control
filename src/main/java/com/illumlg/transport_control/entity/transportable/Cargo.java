package com.illumlg.transport_control.entity.transportable;

import javax.persistence.*;

@Entity
public class Cargo implements Transportable.Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private int category;
    private int mass;
    private String origin;

    public Cargo(String name, String description, int category, int mass, String origin) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.mass = mass;
        this.origin = origin;
    }

    protected Cargo() {

    }

    public int getMass() {
        return mass;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCategory() {
        return category;
    }

    public String getOrigin() {
        return origin;
    }
}
