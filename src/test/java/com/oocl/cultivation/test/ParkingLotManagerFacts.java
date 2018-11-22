package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotManagerFacts {
    @Test
    void should_let_parking_boy_to_park_and_fetch_the_car() {
        ParkingAssistant assistant = ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY);
        final int capacity = 5;
        ParkingLot parkingLot = new ParkingLot(capacity);
        assistant.addParkingLot(parkingLot);

        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingAssistant("assistant-1", assistant);

        Car car = new Car();
        ParkingTicket ticket = manager.park(car, "assistant-1");

        assertEquals(capacity - 1, parkingLot.getAvailableParkingPosition());

        Car fetched = manager.fetch(ticket, "assistant-1");

        assertEquals(car, fetched);
    }

    // TODO: more test cases
    // * should_fail_to_fetch_if_replace_assistant
    // * should_fail_to_fetch_if_assistant_name_does_not_exist
    // * should_show_message_if_assistant_name_does_not_exist
    // * should_show_message_if_assistant_fetch_car_failed
    // * should_show_message_if_assistant_park_car_failed
}