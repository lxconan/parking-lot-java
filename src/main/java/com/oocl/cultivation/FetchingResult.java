package com.oocl.cultivation;

class FetchingResult {
    private final boolean success;
    private final Car car;
    private final String message;

    FetchingResult(String errorMessage) {
        this(false, null, errorMessage);
    }

    FetchingResult(Car car) {
        this(true, car, null);
    }

    private FetchingResult(boolean success, Car car, String message) {
        this.success = success;
        this.car = car;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Car getCar() {
        return car;
    }

    public String getMessage() {
        return message;
    }
}
