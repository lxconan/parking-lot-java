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

    public ParkingResult park1(Car car) {
        if (getAvailableParkingPosition() == 0) {
            return new ParkingResult("The parking lot is full.");
        }

        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return new ParkingResult(ticket);
    }

    public FetchingResult fetch(ParkingTicket ticket) {
        if (ticket == null) {
            return new FetchingResult("Please provide your parking ticket.");
        }

        if (!cars.containsKey(ticket)) {
            return new FetchingResult("Unrecognized parking ticket.");
        }

        Car car = cars.get(ticket);
        cars.remove(ticket);
        return new FetchingResult(car);
    }


    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }
}
