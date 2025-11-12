package com.airxelerate.airxelerateInventory.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data @Builder
public class FlightResponseDTO {
    private Long id;
    private String flightNumber;
    private String carrierCode;
    private Date flightDate;
    private String origin;
    private String destination;
}
