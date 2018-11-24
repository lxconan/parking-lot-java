package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotManager {
    private final ParkingBoy parkingBoy =
        ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY);
    private final Map<String, ParkingBoy> parkingBoys =
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

    public void addParkingBoy(String name, ParkingBoy parkingBoy) {
        parkingBoys.put(name, parkingBoy);
    }

    public ParkingTicket park(Car car, String parkingBoyId) {
        ParkingBoy parkingBoy = getParkingBoy(parkingBoyId);
        return parkingBoy.park(car);
    }

    public Car fetch(ParkingTicket ticket, String parkingBoyId) {
        ParkingBoy parkingBoy = getParkingBoy(parkingBoyId);
        return parkingBoy.fetch(ticket);
    }

    private ParkingBoy getParkingBoy(String parkingBoyId) {
        return parkingBoys.get(parkingBoyId);
    }
}
