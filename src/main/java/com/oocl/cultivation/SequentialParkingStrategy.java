package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

class SequentialParkingStrategy implements ParkingCandidateStrategy {
    public Optional<ParkingLot> findParkingCandidate(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(p -> p.getAvailableParkingPosition() > 0)
            .findFirst();
    }
}
