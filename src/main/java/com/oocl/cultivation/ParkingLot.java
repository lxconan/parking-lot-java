package com.oocl.cultivation;

public class ParkingLot {
    private Car car;

    public ParkingTicket park(Car car) {
        this.car = car;
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket ticket) {
        return car;
    }
}
