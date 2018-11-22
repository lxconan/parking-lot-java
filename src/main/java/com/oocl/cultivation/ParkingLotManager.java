package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotManager {
    private final ParkingAssistant parkingBoy =
        ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY);
    private final Map<String, ParkingAssistant> parkingAssistants =
        new HashMap<>();

    public void addParkingLot(ParkingLot... parkingLots) {
        parkingBoy.addParkingLot(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return parkingBoy.park(car);
    }

    public Car fetch(ParkingTicket ticket) {
        return parkingBoy.fetch(ticket);
    }

    public String getLastErrorMessage() {
        return parkingBoy.getLastErrorMessage();
    }

    public void addParkingAssistant(String name, ParkingAssistant assistant) {
        parkingAssistants.put(name, assistant);
    }

    public ParkingTicket park(Car car, String assistantName) {
        ParkingAssistant assistant = getParkingAssistant(assistantName);
        return assistant.park(car);
    }

    public Car fetch(ParkingTicket ticket, String assistantName) {
        ParkingAssistant assistant = getParkingAssistant(assistantName);
        return assistant.fetch(ticket);
    }

    private ParkingAssistant getParkingAssistant(String assistantName) {
        return parkingAssistants.get(assistantName);
    }
}
