package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingAssistant;
import com.oocl.cultivation.ParkingAssistantFactory;
import com.oocl.cultivation.ParkingLot;

import java.util.stream.IntStream;

class ParkingLotFactory {
    @SuppressWarnings("SameParameterValue")
    static ParkingLot createFullParkingLot(int capacity) {
        return createParkingLot(capacity, capacity);
    }

    static ParkingLot createParkingLot(int capacity, int parked) {
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
