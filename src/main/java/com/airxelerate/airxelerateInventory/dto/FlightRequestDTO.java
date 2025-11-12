package com.airxelerate.airxelerateInventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class FlightRequestDTO {
    @NotBlank(message = "flight number is required")
    @Pattern(regexp = "\\d{4}", message = "Invalid flight number")
    private String flightNumber;

    @Pattern(regexp = "^[A-Z0-9]{2}$", message = "Invalid IATA carrier code")
    private String carrierCode;

    private Date flightDate;

    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid IATA origin airport code")
    private String origin;
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid IATA destination airport code")
    private String destination;
}
