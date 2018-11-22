package com.oocl.cultivation;

public class ParkingException extends RuntimeException {
    public ParkingException() {
    }

    public ParkingException(String message) {
        super(message);
    }
}
