package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    private void setLastError(Throwable error) {
        lastErrorMessage = error.getMessage();
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        try {
            return parkingLot.park(car);
        } catch (ParkingException error) {
            setLastError(error);
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        try {
            return parkingLot.fetch(ticket);
        } catch (ParkingException error) {
            setLastError(error);
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
