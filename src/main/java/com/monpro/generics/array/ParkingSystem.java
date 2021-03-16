package com.monpro.generics.array;

class ParkingSystem {
  private int[] parks;

  ParkingSystem(int big, int medium, int small) {
    parks = new int[] {0, big, medium, small};
  }

  boolean addCar(int carType) {
    if (parks[carType] > 0) {
      parks[carType] -= 1;
      return true;
    } else {
      return false;
    }
  }
}
