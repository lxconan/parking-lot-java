package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (!cars.containsKey(ticket)) {
            return null;
        }

        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;

    }
}
