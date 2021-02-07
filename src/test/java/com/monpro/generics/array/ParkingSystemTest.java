package com.monpro.generics.array;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ParkingSystemTest {
    @Test
    void ParkingSystemBehaviorTest() {
        ParkingSystem parkingSystem = new ParkingSystem(5, 2, 1);
        assertEquals(parkingSystem.addCar(1), true);
        assertEquals(parkingSystem.addCar(2), true);
        assertEquals(parkingSystem.addCar(2), true);
        assertEquals(parkingSystem.addCar(2), false);
        assertEquals(parkingSystem.addCar(3), true);
        assertEquals(parkingSystem.addCar(3), false);

    }
}
