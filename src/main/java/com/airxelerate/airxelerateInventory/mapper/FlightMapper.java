package com.airxelerate.airxelerateInventory.mapper;

import com.airxelerate.airxelerateInventory.dto.FlightRequestDTO;
import com.airxelerate.airxelerateInventory.dto.FlightResponseDTO;
import com.airxelerate.airxelerateInventory.entity.Flight;

public class FlightMapper {
    public static Flight fromFlightRequest(FlightRequestDTO flightRequestDTO){
        return Flight.builder()
                .carrierCode(flightRequestDTO.getCarrierCode())
                .origin(flightRequestDTO.getOrigin())
                .destination(flightRequestDTO.getDestination())
                .flightNumber(flightRequestDTO.getFlightNumber())
                .flightDate(flightRequestDTO.getFlightDate())
                .build();
    }

    public static FlightResponseDTO toFlightResponse(Flight flight){
        return FlightResponseDTO.builder()
                .id(flight.getId())
                .carrierCode(flight.getCarrierCode())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .flightNumber(flight.getFlightNumber())
                .flightDate(flight.getFlightDate())
                .build();
    }
}
