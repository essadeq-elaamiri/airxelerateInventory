package com.airxelerate.airxelerateInventory.service;

import com.airxelerate.airxelerateInventory.dto.FlightRequestDTO;
import com.airxelerate.airxelerateInventory.dto.FlightResponseDTO;
import com.airxelerate.airxelerateInventory.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightService {
    FlightResponseDTO getFlightById(Long id);
    List<FlightResponseDTO> getAllFlights();

    FlightResponseDTO insertFlight(FlightRequestDTO flight);
    boolean deleteFlight(Long id);
}
