package com.oocl.cultivation;

import java.util.stream.IntStream;

class ParkingLotFactory {
    @SuppressWarnings("SameParameterValue")
    public static ParkingLot createFullParkingLot(int capacity) {
        return createParkingLot(capacity, capacity);
    }

    public static ParkingLot createParkingLot(int capacity, int parked) {
        if (capacity < parked) {
            throw new IllegalArgumentException("The parked number should be smaller than or equal to capacity.");
        }

        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingAssistant parkingAssistant = ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY);
        parkingAssistant.addParkingLot(parkingLot);
        IntStream.range(0, parked).forEach(index -> parkingAssistant.park(new Car()));
        return parkingLot;
    }
}
