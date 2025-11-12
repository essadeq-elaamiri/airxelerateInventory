package com.airxelerate.airxelerateInventory.controller;

import com.airxelerate.airxelerateInventory.dto.FlightRequestDTO;
import com.airxelerate.airxelerateInventory.dto.FlightResponseDTO;
import com.airxelerate.airxelerateInventory.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightResponseDTO> getFlight(@PathVariable Long id){
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }

    @PostMapping("/flights")
    public ResponseEntity<FlightResponseDTO> insertFlight(@RequestBody FlightRequestDTO flightRequestDTO){
        return new ResponseEntity<>(flightService.insertFlight(flightRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable Long id ){
        return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
    }
}
