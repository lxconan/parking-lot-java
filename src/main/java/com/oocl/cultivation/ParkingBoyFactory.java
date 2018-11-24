package com.oocl.cultivation;

public class ParkingBoyFactory {
    public static final int PARKING_BOY = 1;
    public static final int SMART_PARKING_BOY = 2;
    public static final int SUPER_SMART_PARKING_BOY = 3;

    public static ParkingBoy create(int type, ParkingLot... parkingLots) {
        ParkingBoy parkingBoy = null;
        switch (type) {
            case PARKING_BOY:
                parkingBoy = new ParkingBoyImpl(new SequentialParkingStrategy());
                break;
            case SMART_PARKING_BOY:
                parkingBoy = new ParkingBoyImpl(new AvailablePositionParkingStrategy());
                break;
            case SUPER_SMART_PARKING_BOY:
                parkingBoy = new ParkingBoyImpl(new AvailableRateParkingStrategy());
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown type: %d", type));
        }

        if (parkingLots != null && parkingLots.length > 0) {
            parkingBoy.addParkingLot(parkingLots);
        }

        return parkingBoy;
    }
}
