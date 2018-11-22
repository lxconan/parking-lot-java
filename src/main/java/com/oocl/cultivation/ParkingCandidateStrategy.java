package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

interface ParkingCandidateStrategy {
    Optional<ParkingLot> findParkingCandidate(List<ParkingLot> parkingLots);
}
