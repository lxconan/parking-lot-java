package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

class AvailableRateParkingStrategy implements ParkingCandidateStrategy {
    @Override
    public Optional<ParkingLot> findParkingCandidate(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(p -> p.getAvailableParkingPosition() > 0)
                .max((left, right) -> {
                    double leftEmptyRate = (double) left.getAvailableParkingPosition() / left.getCapacity();
                    double rightEmptyRate = (double) right.getAvailableParkingPosition() / right.getCapacity();
                    return Double.compare(leftEmptyRate, rightEmptyRate);
                });
    }
}
