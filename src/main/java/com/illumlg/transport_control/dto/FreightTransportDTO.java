package com.illumlg.transport_control.dto;

import com.illumlg.transport_control.entity.transportable.CargoContainer;

public class FreightTransportDTO {
    private String name;
    private CargoContainer content;

    public FreightTransportDTO() {

    }

    public FreightTransportDTO(String name, CargoContainer content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CargoContainer getContent() {
        return content;
    }

    public void setContent(CargoContainer content) {
        this.content = content;
    }
}
