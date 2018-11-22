package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (getAvailableParkingPosition() == 0) {
            throw new ParkingException("The parking lot is full.");
        }

        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null) {
            throw new ParkingException("Please provide your parking ticket.");
        }

        if (!cars.containsKey(ticket)) {
            throw new ParkingException("Unrecognized parking ticket.");
        }

        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }
}
