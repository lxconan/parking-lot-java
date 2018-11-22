package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    @Test
    void should_parking_in_the_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(3);
        ParkingBoy parkingBoy = createParkingBoy();
        parkingBoy.addParkingLot(firstParkingLot, secondParkingLot);

        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(3, secondParkingLot.getAvailableParkingPosition());
    }

    @Test
    void should_parking_in_the_second_parking_lot_if_the_first_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(3);
        ParkingBoy parkingBoy = createParkingBoy();
        parkingBoy.addParkingLot(firstParkingLot, secondParkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals(0, firstParkingLot.getAvailableParkingPosition());
        assertEquals(2, secondParkingLot.getAvailableParkingPosition());
    }

    private static ParkingBoy createParkingBoy() {
        return new ParkingBoy();
    }
}
