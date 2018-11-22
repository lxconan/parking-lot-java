package com.oocl.cultivation;

class ParkingResult {
    private final boolean success;
    private final ParkingTicket ticket;
    private final String message;

    ParkingResult(String errorMessage) {
        this(false, null, errorMessage);
    }

    ParkingResult(ParkingTicket ticket) {
        this(true, ticket, null);
    }

    private ParkingResult(boolean success, ParkingTicket ticket, String message) {
        this.success = success;
        this.ticket = ticket;
        this.message = message;
    }

    boolean isSuccess() {
        return success;
    }

    ParkingTicket getTicket() {
        return ticket;
    }

    String getMessage() {
        return message;
    }
}
