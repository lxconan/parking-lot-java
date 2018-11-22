package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static com.oocl.cultivation.ParkingLotFactory.createParkingLot;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperSmartParkingBoyFacts {
    @Test
    void should_park_to_parking_lots_with_more_empty_rate() {
        ParkingLot firstParkingLotWithHalfEmptyRate = createParkingLot(20, 10);
        ParkingLot secondParkingLotWithFullEmptyRate = createParkingLot(4, 0);
        ParkingLot thirdParkingLotWithOneThirdEmptyRate = createParkingLot(60, 40);
        ParkingAssistant superSmartParkingBoy = createSuperSmartParkingBoy();
        superSmartParkingBoy.addParkingLot(
            firstParkingLotWithHalfEmptyRate,
            secondParkingLotWithFullEmptyRate,
            thirdParkingLotWithOneThirdEmptyRate
        );

        superSmartParkingBoy.park(new Car());

        assertEquals(10, firstParkingLotWithHalfEmptyRate.getAvailableParkingPosition());
        assertEquals(3, secondParkingLotWithFullEmptyRate.getAvailableParkingPosition());
        assertEquals(20, thirdParkingLotWithOneThirdEmptyRate.getAvailableParkingPosition());
    }

    private static ParkingAssistant createSuperSmartParkingBoy() {
        return ParkingAssistantFactory.create(ParkingAssistantFactory.SUPER_SMART_PARKING_BOY);
    }
}
