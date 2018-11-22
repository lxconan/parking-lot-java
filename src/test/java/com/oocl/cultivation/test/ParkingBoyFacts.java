package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingAssistant;
import com.oocl.cultivation.ParkingAssistantFactory;
import com.oocl.cultivation.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    @Test
    void should_parking_in_the_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(3);
        ParkingAssistant parkingBoy = createParkingBoy();
        parkingBoy.addParkingLot(firstParkingLot, secondParkingLot);

        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(3, secondParkingLot.getAvailableParkingPosition());
    }

    private static ParkingAssistant createParkingBoy() {
        return ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY);
    }
}
