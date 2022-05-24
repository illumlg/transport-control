package com.illumlg.transport_control.dto;

import com.illumlg.transport_control.entity.transportable.PassengerContainer;

public class PassengerTransportDTO {
    private String name;
    private PassengerContainer content;

    public PassengerTransportDTO() {

    }

    public PassengerTransportDTO(String name, PassengerContainer content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassengerContainer getContent() {
        return content;
    }

    public void setContent(PassengerContainer content) {
        this.content = content;
    }
}
