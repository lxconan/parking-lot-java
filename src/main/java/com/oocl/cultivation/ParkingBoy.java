package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingResult parkingResult = parkingLot.park1(car);
        if (parkingResult.isSuccess()) {
            return parkingResult.getTicket();
        } else {
            lastErrorMessage = parkingResult.getMessage();
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        FetchingResult fetchingResult = parkingLot.fetch(ticket);
        if (fetchingResult.isSuccess()) {
            return fetchingResult.getCar();
        } else {
            lastErrorMessage = fetchingResult.getMessage();
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
