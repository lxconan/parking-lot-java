package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyFacts {
    @Test
    void should_park_to_parking_lot_who_has_more_empty_position() {
        ParkingLot firstParkingLotWith2Positions = new ParkingLot(2);
        ParkingLot secondParkingLotWith3Positions = new ParkingLot(3);
        ParkingLot thirdParkingLotWith1Position = new ParkingLot(1);
        ParkingAssistant smartParkingBoy = createSmartParkingBoy();
        smartParkingBoy.addParkingLot(
            firstParkingLotWith2Positions, secondParkingLotWith3Positions, thirdParkingLotWith1Position);

        smartParkingBoy.park(new Car());

        assertEquals(2, firstParkingLotWith2Positions.getAvailableParkingPosition());
        assertEquals(2, secondParkingLotWith3Positions.getAvailableParkingPosition());
        assertEquals(1, thirdParkingLotWith1Position.getAvailableParkingPosition());
    }

    private static ParkingAssistant createSmartParkingBoy() {
        return ParkingAssistantFactory.create(ParkingAssistantFactory.SMART_PARKING_BOY);
    }
}
