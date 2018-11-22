package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    @ParameterizedTest
    @MethodSource("createParkingBoy")
    void should_parking_in_the_first_parking_lot(ParkingAssistant assistant) {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(3);
        assistant.addParkingLot(firstParkingLot, secondParkingLot);

        assistant.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(3, secondParkingLot.getAvailableParkingPosition());
    }

    private static Stream<ParkingAssistant> createParkingBoy() {
        return Stream.of(
            ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY),
            new ParkingLotManagerProxy(new ParkingLotManager())
        );
    }
}
