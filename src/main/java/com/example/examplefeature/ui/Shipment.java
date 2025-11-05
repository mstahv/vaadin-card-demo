package com.example.examplefeature.ui;

public record Shipment(String id, String route, String shipmentDescription, TransitStatus status, String driver) {
}
