package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingBoyFactory;
import com.oocl.cultivation.ParkingLot;
import org.junit.jupiter.api.Test;

import static com.oocl.cultivation.test.ParkingLotFactory.createParkingLot;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperSmartParkingBoyFacts {
    @Test
    void should_park_to_parking_lots_with_more_empty_rate() {
        ParkingLot firstParkingLotWithHalfEmptyRate = createParkingLot(20, 10);
        ParkingLot secondParkingLotWithFullEmptyRate = createParkingLot(4, 0);
        ParkingLot thirdParkingLotWithOneThirdEmptyRate = createParkingLot(60, 40);
        ParkingBoy superSmartParkingBoy = createSuperSmartParkingBoy();
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

    private static ParkingBoy createSuperSmartParkingBoy() {
        return ParkingBoyFactory.create(ParkingBoyFactory.SUPER_SMART_PARKING_BOY);
    }
}