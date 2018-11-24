package com.oocl.cultivation.test;

import com.oocl.cultivation.*;

class ParkingLotManagerProxy implements ParkingBoy {
    private final ParkingLotManager manager;

    public ParkingLotManagerProxy(ParkingLotManager manager) {
        this.manager = manager;
    }

    @Override
    public void addParkingLot(ParkingLot... parkingLots) {
        manager.addParkingLot(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        return manager.park(car);
    }

    @Override
    public Car fetch(ParkingTicket ticket) {
        return manager.fetch(ticket);
    }

    @Override
    public String getLastErrorMessage() {
        return manager.getLastErrorMessage();
    }
}
