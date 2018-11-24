package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotManagerFacts {
    @Test
    void should_let_parking_boy_to_park_and_fetch_the_car() {
        ParkingBoy parkingBoy = ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY);
        ParkingLot parkingLot = ParkingLotFactory.createEmptyParkingLot();
        parkingBoy.addParkingLot(parkingLot);

        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy("parkingBoy-1", parkingBoy);

        Car car = new Car();
        ParkingTicket ticket = manager.park(car, "parkingBoy-1");
        assertEquals(parkingLot.getCapacity() - 1, parkingLot.getAvailableParkingPosition());

        Car fetched = manager.fetch(ticket, "parkingBoy-1");
        assertEquals(car, fetched);
    }

    // TODO: more test cases
    // * should_fail_to_fetch_if_replace_assistant
    // * should_fail_to_fetch_if_assistant_name_does_not_exist
    // * should_show_message_if_assistant_name_does_not_exist
    // * should_show_message_if_assistant_fetch_car_failed
    // * should_show_message_if_assistant_park_car_failed
}