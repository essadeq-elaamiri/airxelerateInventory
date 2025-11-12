package com.airxelerate.airxelerateInventory.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightRequestDTO {
    private String flightNumber;
    private String carrierCode;
    private Date flightDate;
    private String origin;
    private String destination;
}
