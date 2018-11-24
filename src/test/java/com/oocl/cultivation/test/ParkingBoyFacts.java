package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    @ParameterizedTest
    @MethodSource("createParkingBoy")
    void should_parking_in_the_first_parking_lot(ParkingBoy parkingBoy) {
        ParkingLot firstParkingLot = ParkingLotFactory.createEmptyParkingLot(1);
        ParkingLot secondParkingLot = ParkingLotFactory.createEmptyParkingLot(3);
        parkingBoy.addParkingLot(firstParkingLot, secondParkingLot);

        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(3, secondParkingLot.getAvailableParkingPosition());
    }

    private static Stream<ParkingBoy> createParkingBoy() {
        return Stream.of(
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY),
            new ParkingLotManagerProxy(new ParkingLotManager())
        );
    }
}
