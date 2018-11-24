package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void should_not_fetch_cars_from_parking_boy_who_did_not_manage_the_parking_lot() {
        ParkingBoy parkingBoyWithoutCar = ParkingBoyFactory.create(
            ParkingBoyFactory.PARKING_BOY,
            ParkingLotFactory.createEmptyParkingLot());
        ParkingBoy parkingBoyWithCar = ParkingBoyFactory.create(
            ParkingBoyFactory.PARKING_BOY,
            ParkingLotFactory.createEmptyParkingLot());
        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy("parking-boy-without-car", parkingBoyWithoutCar);
        manager.addParkingBoy("parking-boy-with-car", parkingBoyWithCar);
        ParkingTicket ticket = manager.park(new Car(), "parking-boy-with-car");

        Car fetched = manager.fetch(ticket, "parking-boy-without-car");

        assertNull(fetched);
    }

    @Test
    void should_display_error_message_if_manager_initiated_fetch_job_for_parking_boy_failed() {
        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy(
            "parking-boy",
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY,
                ParkingLotFactory.createEmptyParkingLot())
        );

        Car fetch = manager.fetch(new ParkingTicket(), "parking-boy");

        String message = manager.getLastErrorMessage();
        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_display_error_message_if_manager_initiated_parking_job_for_parking_boy_failed() {
        int notImportantAtAll = 2;

        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy(
            "parking-boy",
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY,
                ParkingLotFactory.createFullParkingLot(notImportantAtAll))
        );

        manager.park(new Car(), "parking-boy");

        String message = manager.getLastErrorMessage();
        assertEquals("The parking lot is full.", message);
    }

    @Test
    void should_not_park_car_if_parking_boys_does_not_exist() {
        ParkingLotManager manager = new ParkingLotManager();

        ParkingTicket ticket = manager.park(new Car(), "not-exist");

        assertNull(ticket);
    }

    @Test
    void should_get_message_if_parking_boy_does_not_exist_when_parking() {
        ParkingLotManager manager = new ParkingLotManager();
        manager.park(new Car(), "not-exist");

        String message = manager.getLastErrorMessage();

        assertEquals("Cannot find parking boy: not-exist", message);
    }

    @Test
    void should_not_fetch_car_if_parking_boy_name_does_not_exist() {
        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy(
            "parking-boy",
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY, ParkingLotFactory.createEmptyParkingLot()));
        ParkingTicket ticket = manager.park(new Car(), "parking-boy");
        assertNotNull(ticket);

        Car fetched = manager.fetch(ticket, "not-exist");

        assertNull(fetched);
    }

    @Test
    void should_get_message_if_parking_boy_does_not_exist_when_fetching() {
        ParkingLotManager manager = new ParkingLotManager();
        manager.addParkingBoy(
            "parking-boy",
            ParkingBoyFactory.create(ParkingBoyFactory.PARKING_BOY, ParkingLotFactory.createEmptyParkingLot()));
        ParkingTicket ticket = manager.park(new Car(), "parking-boy");
        manager.fetch(ticket, "not-exist");

        String message = manager.getLastErrorMessage();

        assertEquals("Cannot find parking boy: not-exist", message);
    }
}