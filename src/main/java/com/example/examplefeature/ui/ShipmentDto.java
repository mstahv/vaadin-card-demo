package com.example.examplefeature.ui;

public record ShipmentDto(String id, String route, String shipmentDescription, TransitStatus status, String driver, String truckImageUrl) {
}
