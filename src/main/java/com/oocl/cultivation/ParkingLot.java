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

    ParkingResult park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return new ParkingResult(ticket);
    }

    FetchingResult fetch(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return new FetchingResult(car);
    }

    boolean containsTicket(ParkingTicket ticket) {
        return cars.containsKey(ticket);
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    @SuppressWarnings("WeakerAccess")
    public int getCapacity() {
        return capacity;
    }
}
