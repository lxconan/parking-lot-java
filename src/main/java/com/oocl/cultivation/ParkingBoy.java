package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        try {
            return parkingLot.fetch(ticket);
        } catch (ParkingException error) {
            lastErrorMessage = error.getMessage();
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
