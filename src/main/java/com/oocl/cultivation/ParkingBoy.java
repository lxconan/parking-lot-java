package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {

        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = parkingLot.park(car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        return parkingLot.fetch(ticket);
    }
}
