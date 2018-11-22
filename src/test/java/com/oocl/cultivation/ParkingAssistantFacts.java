package com.oocl.cultivation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.oocl.cultivation.ParkingLotFactory.createFullParkingLot;
import static org.junit.jupiter.api.Assertions.*;

class ParkingAssistantFacts {
    private static Stream<ParkingAssistant> createParkingAssistant() {
        return Stream.of(
            ParkingAssistantFactory.create(ParkingAssistantFactory.PARKING_BOY),
            ParkingAssistantFactory.create(ParkingAssistantFactory.SMART_PARKING_BOY),
            ParkingAssistantFactory.create(ParkingAssistantFactory.SUPER_SMART_PARKING_BOY)
        );
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_park_a_car_to_a_parking_lot_and_get_it_back(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_not_fetch_any_car_once_ticket_is_wrong(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_query_message_once_the_ticket_is_wrong(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_not_fetch_any_car_once_ticket_is_not_provided(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_query_message_once_ticket_is_not_provided(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);

        parkingBoy.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_not_fetch_any_car_once_ticket_has_been_used(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_query_error_message_for_used_ticket(ParkingAssistant parkingBoy) {
        ParkingLot parkingLot = new ParkingLot();
        parkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            parkingBoy.getLastErrorMessage()
        );
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position(ParkingAssistant parkingBoy) {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingBoy.addParkingLot(parkingLot);

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_get_message_if_there_is_not_enough_position(ParkingAssistant parkingBoy) {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingBoy.addParkingLot(parkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_not_park_when_all_parking_lots_are_full(ParkingAssistant parkingBoy) {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingBoy.addParkingLot(firstParkingLot, secondParkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingAssistant")
    void should_parking_in_the_second_parking_lot_if_the_first_is_full(ParkingAssistant parkingAssistant) {
        ParkingLot firstFullParkingLot = createFullParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        parkingAssistant.addParkingLot(firstFullParkingLot, secondParkingLot);

        parkingAssistant.park(new Car());

        assertEquals(0, firstFullParkingLot.getAvailableParkingPosition());
        assertEquals(2, secondParkingLot.getAvailableParkingPosition());
    }
}
