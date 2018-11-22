package com.oocl.cultivation;

public interface ParkingAssistant {
    void addParkingLot(ParkingLot... parkingLots);
    ParkingTicket park(Car car);
    Car fetch(ParkingTicket ticket);
    String getLastErrorMessage();
}
