package com.airxelerate.airxelerateInventory.service;

import com.airxelerate.airxelerateInventory.dto.FlightRequestDTO;
import com.airxelerate.airxelerateInventory.dto.FlightResponseDTO;
import com.airxelerate.airxelerateInventory.entity.Flight;
import com.airxelerate.airxelerateInventory.exception.FlightNotFoundException;
import com.airxelerate.airxelerateInventory.mapper.FlightMapper;
import com.airxelerate.airxelerateInventory.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;

    @Override
    public FlightResponseDTO getFlightById(Long id) {
        log.info("Getting Flight with ID: {}", id);
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new FlightNotFoundException("Flight with id: "+id+" not found!"));
        return FlightMapper.toFlightResponse(flight);
    }

    @Override
    public List<FlightResponseDTO> getAllFlights() {
        log.info("Getting All Flights");
        return flightRepository.findAll().stream().map(FlightMapper::toFlightResponse).toList();
    }

    @Override
    public FlightResponseDTO insertFlight(FlightRequestDTO flight) {
        log.info("Inserting Flight: {}",flight);
        Flight flightToInsert = FlightMapper.fromFlightRequest(flight);
        Flight savedFlight = flightRepository.save(flightToInsert);
        return FlightMapper.toFlightResponse(savedFlight);
    }

    @Override
    public boolean deleteFlight(Long id) {
        log.info("Deleting Flight With ID: {}",id);
        FlightResponseDTO flightToDelete = getFlightById(id);
        flightRepository.deleteById(id);
        return true;
    }
}
