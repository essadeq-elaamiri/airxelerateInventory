package com.airxelerate.airxelerateInventory.controller;

import com.airxelerate.airxelerateInventory.dto.FlightRequestDTO;
import com.airxelerate.airxelerateInventory.dto.FlightResponseDTO;
import com.airxelerate.airxelerateInventory.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Flights Management", description = "Endpoints for managing flights")
public class FlightController {
    private final FlightService flightService;

    @Operation(summary = "Get all flights", description = "Retrieve all available flights")
    @GetMapping("/flights")
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @Operation(summary = "Get a flight by Id")
    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightResponseDTO> getFlight(@PathVariable Long id){
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }

    @Operation(summary = "Insert a flight")
    @PostMapping("/flights")
    public ResponseEntity<FlightResponseDTO> insertFlight(@Valid @RequestBody FlightRequestDTO flightRequestDTO){
        return new ResponseEntity<>(flightService.insertFlight(flightRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a flight")
    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable Long id ){
        return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
    }
}
