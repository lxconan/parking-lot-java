package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotManager {
    private final ParkingBoy parkingBoy =
        ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY);
    private final Map<String, ParkingBoy> parkingBoys =
        new HashMap<>();
    private String lastErrorMessage = null;

    public void addParkingLot(ParkingLot... parkingLots) {
        parkingBoy.addParkingLot(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = parkingBoy.park(car);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car fetched = parkingBoy.fetch(ticket);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return fetched;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void addParkingBoy(String name, ParkingBoy parkingBoy) {
        parkingBoys.put(name, parkingBoy);
    }

    public ParkingTicket park(Car car, String parkingBoyId) {
        ParkingBoy parkingBoy = getParkingBoy(parkingBoyId);
        if (parkingBoy == null) {
            return null;
        }

        return parkingBoy.park(car);
    }

    public Car fetch(ParkingTicket ticket, String parkingBoyId) {
        ParkingBoy parkingBoy = getParkingBoy(parkingBoyId);
        if (parkingBoy == null) {
            return null;
        }
        Car fetched = parkingBoy.fetch(ticket);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return fetched;
    }

    private ParkingBoy getParkingBoy(String parkingBoyId) {
        return parkingBoys.get(parkingBoyId);
    }
}
