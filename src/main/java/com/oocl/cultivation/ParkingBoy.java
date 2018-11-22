package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ParkingBoy implements ParkingAssistant {
    private final List<ParkingLot> parkingLots = new ArrayList<>();
    private final ParkingCandidateStrategy parkingCandidateStrategy;
    private String lastErrorMessage;

    ParkingBoy(ParkingCandidateStrategy parkingCandidateStrategy) {
        this.parkingCandidateStrategy = parkingCandidateStrategy;
    }

    @Override
    public void addParkingLot(ParkingLot... parkingLots) {
        // TODO: security check.
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingResult parkingResult = tryPark(car);
        if (parkingResult.isSuccess()) {
            lastErrorMessage = null;
            return parkingResult.getTicket();
        } else {
            lastErrorMessage = parkingResult.getMessage();
            return null;
        }
    }

    private ParkingResult tryPark(Car car) {
        Optional<ParkingLot> parkingLot = parkingCandidateStrategy.findParkingCandidate(parkingLots);
        if (!parkingLot.isPresent()) {
            return new ParkingResult("The parking lot is full.");
        }

        return parkingLot.get().park(car);
    }

    @Override
    public Car fetch(ParkingTicket ticket) {
        FetchingResult fetchingResult = tryFetch(ticket);
        if (fetchingResult.isSuccess()) {
            lastErrorMessage = null;
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
