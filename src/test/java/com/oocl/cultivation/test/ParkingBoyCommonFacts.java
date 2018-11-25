package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.oocl.cultivation.test.ParkingLotFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyCommonFacts {
    private static Stream<ParkingBoy> createParkingBoys() {
        return Stream.of(
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY),
            ParkingBoyFactory.create(ParkingBoyFactory.SMART_PARKING_BOY),
            ParkingBoyFactory.create(ParkingBoyFactory.SUPER_SMART_PARKING_BOY),
            new ParkingLotManagerProxy(new ParkingLotManager())
        );
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_park_a_car_to_a_parking_lot_and_get_it_back(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_not_display_error_message_if_park_and_fetch_are_succeeded(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        Car car = new Car();
        ParkingTicket invalidTicket = new ParkingTicket();

        parkingBoy.fetch(invalidTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(car);
        assertNull(parkingBoy.getLastErrorMessage());

        parkingBoy.fetch(invalidTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        parkingBoy.fetch(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
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
    @MethodSource("createParkingBoys")
    void should_not_fetch_any_car_once_ticket_is_wrong(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket validTicket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(validTicket));
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_query_message_once_the_ticket_is_wrong(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_not_fetch_any_car_once_ticket_is_not_provided(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_query_message_once_ticket_is_not_provided(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());

        parkingBoy.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_not_fetch_any_car_once_ticket_has_been_used(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_query_error_message_for_used_ticket(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createEmptyParkingLot());
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
    @MethodSource("createParkingBoys")
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createFullParkingLot());

        ParkingTicket ticket = parkingBoy.park(new Car());

        assertNull(ticket);
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_get_message_if_there_is_not_enough_position(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createFullParkingLot());

        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_not_park_when_all_parking_lots_are_full(ParkingBoy parkingBoy) {
        parkingBoy.addParkingLot(createFullParkingLot(), createFullParkingLot());

        ParkingTicket ticket = parkingBoy.park(new Car());

        assertNull(ticket);
        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("createParkingBoys")
    void should_parking_in_the_second_parking_lot_if_the_first_is_full(ParkingBoy parkingBoy) {
        ParkingLot firstFullParkingLot = createFullParkingLot();
        ParkingLot secondParkingLot = createEmptyParkingLot(3);
        parkingBoy.addParkingLot(firstFullParkingLot, secondParkingLot);

        parkingBoy.park(new Car());

        assertEquals(0, firstFullParkingLot.getAvailableParkingPosition());
        assertEquals(2, secondParkingLot.getAvailableParkingPosition());
    }
}
