package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingBoyFactory;
import com.oocl.cultivation.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyFacts {
    @Test
    void should_park_to_parking_lot_who_has_more_empty_position() {
        ParkingLot firstParkingLotWith2Positions = ParkingLotFactory.createEmptyParkingLot(2);
        ParkingLot secondParkingLotWith3Positions = ParkingLotFactory.createEmptyParkingLot(3);
        ParkingLot thirdParkingLotWith1Position = ParkingLotFactory.createEmptyParkingLot(1);
        ParkingBoy smartParkingBoy = createSmartParkingBoy();
        smartParkingBoy.addParkingLot(
            firstParkingLotWith2Positions, secondParkingLotWith3Positions, thirdParkingLotWith1Position);

        smartParkingBoy.park(new Car());

        assertEquals(
            firstParkingLotWith2Positions.getCapacity(),
            firstParkingLotWith2Positions.getAvailableParkingPosition());
        assertEquals(
            secondParkingLotWith3Positions.getCapacity() - 1,
            secondParkingLotWith3Positions.getAvailableParkingPosition());
        assertEquals(
            thirdParkingLotWith1Position.getCapacity(),
            thirdParkingLotWith1Position.getAvailableParkingPosition());
    }

    private static ParkingBoy createSmartParkingBoy() {
        return ParkingBoyFactory.create(ParkingBoyFactory.SMART_PARKING_BOY);
    }
}
