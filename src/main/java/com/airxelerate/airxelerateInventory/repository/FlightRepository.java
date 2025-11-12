package com.airxelerate.airxelerateInventory.repository;

import com.airxelerate.airxelerateInventory.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
