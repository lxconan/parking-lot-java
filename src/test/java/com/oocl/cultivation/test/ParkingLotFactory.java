package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingBoyFactory;
import com.oocl.cultivation.ParkingLot;

import java.util.stream.IntStream;

class ParkingLotFactory {
    static ParkingLot createFullParkingLot() {
        return createFullParkingLot(10);
    }

    @SuppressWarnings("SameParameterValue")
    static ParkingLot createFullParkingLot(int capacity) {
        return createParkingLot(capacity, capacity);
    }

    @SuppressWarnings("SameParameterValue")
    static ParkingLot createEmptyParkingLot(int capacity) {
        return new ParkingLot(capacity);
    }

    static ParkingLot createEmptyParkingLot() {
        return new ParkingLot();
    }

    static ParkingLot createParkingLot(int capacity, int parked) {
        if (capacity < parked) {
            throw new IllegalArgumentException("The parked number should be smaller than or equal to capacity.");
        }

        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY);
        parkingBoy.addParkingLot(parkingLot);
        IntStream.range(0, parked).forEach(index -> parkingBoy.park(new Car()));
        return parkingLot;
    }
}
