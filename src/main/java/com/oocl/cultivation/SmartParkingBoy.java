package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy implements ParkingAssistant {
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
        Optional<ParkingLot> parkingLot = findParkingCandidate();
        if (!parkingLot.isPresent()) {
            return new ParkingResult("The parking lot is full.");
        }

        return parkingLot.get().park(car);
    }

    private Optional<ParkingLot> findParkingCandidate() {
        return parkingLots.stream()
            .filter(p -> p.getAvailableParkingPosition() > 0)
            .max((left, right) -> {
                int leftCount = left.getAvailableParkingPosition();
                int rightCount = right.getAvailableParkingPosition();
                return Integer.compare(leftCount, rightCount);
            });
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
