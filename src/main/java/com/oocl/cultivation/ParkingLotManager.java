package com.oocl.cultivation;

public class ParkingLotManager {
    private final ParkingAssistant parkingBoy = ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY);

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
}
