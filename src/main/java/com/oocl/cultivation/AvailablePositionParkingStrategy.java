package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

class AvailablePositionParkingStrategy implements ParkingCandidateStrategy {
    @Override
    public Optional<ParkingLot> findParkingCandidate(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(p -> p.getAvailableParkingPosition() > 0)
            .max((left, right) -> {
                int leftCount = left.getAvailableParkingPosition();
                int rightCount = right.getAvailableParkingPosition();
                return Integer.compare(leftCount, rightCount);
            });
    }
}
