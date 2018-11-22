package com.oocl.cultivation;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingResult parkingResult = tryPark(car);
        if (parkingResult.isSuccess()) {
            return parkingResult.getTicket();
        } else {
            lastErrorMessage = parkingResult.getMessage();
            return null;
        }
    }

    private ParkingResult tryPark(Car car) {
        ParkingResult lastResult = null;
        for (ParkingLot parkingLot : parkingLots) {
            lastResult = parkingLot.park(car);
            if (lastResult.isSuccess()) break;
        }

        return lastResult;
    }

    public Car fetch(ParkingTicket ticket) {
        FetchingResult fetchingResult = tryFetch(ticket);
        if (fetchingResult.isSuccess()) {
            return fetchingResult.getCar();
        } else {
            lastErrorMessage = fetchingResult.getMessage();
            return null;
        }
    }

    private FetchingResult tryFetch(ParkingTicket ticket) {
        FetchingResult lastFetchResult = null;
        for (ParkingLot parkingLot : parkingLots) {
            lastFetchResult = parkingLot.fetch(ticket);
            if (lastFetchResult.isSuccess()) {
                break;
            }
        }

        return lastFetchResult;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
