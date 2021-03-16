package com.monpro.generics.array;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class ParkingSystemTest {
  @Test
  void ParkingSystemBehaviorTest() {
    ParkingSystem parkingSystem = new ParkingSystem(5, 2, 1);
    assertTrue(parkingSystem.addCar(1));
    assertTrue(parkingSystem.addCar(2));
    assertTrue(parkingSystem.addCar(2));
    assertFalse(parkingSystem.addCar(2));
    assertTrue(parkingSystem.addCar(3));
    assertFalse(parkingSystem.addCar(3));
  }
}
