package com.oocl.cultivation;

public class ParkingAssistantFactory {
    public static final int PARKING_BOY = 1;
    public static final int SMART_PARKING_BOY = 2;
    public static final int SUPER_SMART_PARKING_BOY = 3;

    public static ParkingAssistant create(int type) {
        switch (type) {
            case PARKING_BOY: return new ParkingBoy(new SequentialParkingStrategy());
            case SMART_PARKING_BOY: return new ParkingBoy(new AvailablePositionParkingStrategy());
            case SUPER_SMART_PARKING_BOY: return new ParkingBoy(new AvailableRateParkingStrategy());
            default:
                throw new IllegalArgumentException(String.format("Unknown type: %d", type));
        }
    }
}
