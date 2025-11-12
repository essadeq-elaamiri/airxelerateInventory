package com.airxelerate.airxelerateInventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String carrierCode;
    private Date flightDate;
    private String origin;
    private String destination;
}
