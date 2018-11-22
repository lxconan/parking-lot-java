package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy implements ParkingAssistant {
    private final List<ParkingLot> parkingLots = new ArrayList<>();
    private String lastErrorMessage;

    @Override
    public void addParkingLot(ParkingLot... parkingLots) {
        // TODO: security check.
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    @Override
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

    @Override
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

    @Override
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
